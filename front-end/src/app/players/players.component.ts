import {Component} from '@angular/core';
import {PlayerService} from "../services/player.service";
import {map, Observable} from "rxjs";
import {Player} from "../models/player.model";
import {ActivatedRoute, Router} from "@angular/router";
import {DiceModel} from "../models/dice.model";

@Component({
  selector: 'epf-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.scss']
})
export class PlayersComponent {
  players: Observable<Player[]> = new Observable<Player[]>()
  nb: number = 0;
  computerPredictionResult: string[][] = [[""]];
  listOfDiceValues = "";
  bet: string[] = ["",""];
  result: string = "";
  rules: string = "";

  constructor(private _route: ActivatedRoute, private playerService: PlayerService, private router: Router) {
    this.players = playerService.findAll()
  }

  createPlayers(event: any, nb: number | undefined) {
    event.stopPropagation()
    this.listOfDiceValues = "";
    this.players = new Observable<Player[]>()
    this.players = this.playerService.createPlayers(nb)
    this.players.forEach(element => {
      element.forEach(player => {
        player.dices.forEach(dice => {
          this.listOfDiceValues += dice.diceValue.toString() + "&"
        })
      })
    }).then(() =>
      console.log(this.listOfDiceValues)
  );
  }

  deletePlayer(event: any, player: Player) {
    event.stopPropagation();
    this.playerService.deletePlayers(player).subscribe(() => this.router.navigate(["players"], {skipLocationChange: true}));
  }

  playerBet(event: any){
    event.stopPropagation();
    this.playerService.playerBet(this.bet, this.listOfDiceValues, this.computerPredictionResult).subscribe(result => {
      this.result = result[0];
      console.log(this.result)
    })
  }

  computerPrediction(event: any) {
    event.stopPropagation();
    this.listOfDiceValues = "";
    this.players.forEach(element => {
      element.forEach(player => {
        player.dices.forEach(dice => {
          this.listOfDiceValues += dice.diceValue.toString() + "&"
        })
      })
    }).then(() =>
      this.playerService.computerPrediction(this.listOfDiceValues.slice(0, -1), this.nb).subscribe(result => {
        this.computerPredictionResult = result.slice(0, -1);
        // @ts-ignore
        this.rules = result.at(-1)[0];
        console.log(this.computerPredictionResult)
    })
    );
  }
}
