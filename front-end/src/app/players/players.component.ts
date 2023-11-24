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
  players: Observable<Player[]>
  nb: number | undefined;
  computerPredictionResult: string[][] = [[""]];
  playerWin: Observable<String> | undefined;
  listOfDiceValues = "";
  bet: string[] = ["",""];

  constructor(private _route: ActivatedRoute, private playerService: PlayerService, private router: Router) {
    this.players = playerService.findAll()
  }

  createPlayers(event: any, nb: number | undefined) {
    event.stopPropagation()
    this.players = this.playerService.createPlayers(nb)
    /////////////////////// GETTING THE DATA FOR FRONT-END LOGIC
    let test_players = this.playerService.createPlayers(nb)
    test_players.forEach(element => {
      element.forEach(player => {
        //console.log(player.dices)
      })
    });
    ////////////////////////////////
  }

  deletePlayer(event: any, player: Player) {
    event.stopPropagation();
    this.playerService.deletePlayers(player).subscribe(() => this.router.navigate(["players"], {skipLocationChange: true}));
  }

  playerBet(event: any){
    event.stopPropagation();
    this.playerWin = this.playerService.playerBet(this.bet, this.listOfDiceValues, this.computerPredictionResult)
  }

  computerPrediction(event: any) {
    event.stopPropagation();
    this.players.forEach(element => {
      element.forEach(player => {
        player.dices.forEach(dice => {
          this.listOfDiceValues += dice.diceValue.toString() + "&"
        })
      })
    }).then(() =>
      this.playerService.computerPrediction(this.listOfDiceValues.slice(0, -1)).subscribe(result => {
        this.computerPredictionResult = result;
        console.log(this.computerPredictionResult)
    })
    );
  }
}
