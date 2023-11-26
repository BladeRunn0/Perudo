import {Component} from '@angular/core';
import {PlayerService} from "../services/player.service";
import {map, Observable} from "rxjs";
import {Player} from "../models/player.model";
import {ActivatedRoute, Router} from "@angular/router";
import {DiceModel} from "../models/dice.model";
import {SharedService} from "../services/shared-service.service";

@Component({
  selector: 'epf-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.scss']
})
export class PlayersComponent {
  players: Observable<Player[]>
  nbComputers: number | undefined;
  computerPredictionResult: string[][] = [[""]];
  listOfDiceValues = "";
  bet: string[] = ["",""];
  result: string = "";
  rules: string = "";

  constructor(
    private _route: ActivatedRoute,
    private playerService: PlayerService,
    private router: Router,
    private sharedService: SharedService
  ) {
    this.players = playerService.findAll()
  }

  ngOnInit(): void {
    this.sharedService.getNbComputers().subscribe(value => {
      this.nbComputers = value;
      this.createPlayers(this.nbComputers);
      // Utilisez la valeur récupérée comme nécessaire dans votre autre composant
    });
  }

  computersHeaders(count: number | undefined): string[] {
    if (count !== undefined) {
      const headers: string[] = [];
      for (let i = 1; i <= count; i++) {
        headers.push(`Ordinateur ${i}`);
      }
      return headers;
    } else {
      return []; // Retourne un tableau vide si count est undefined
    }
  }

  createPlayers(nb: number | undefined) {
    this.players = this.playerService.createPlayers(nb);
    /////////////////////// GETTING THE DATA FOR FRONT-END LOGIC
    //let test_players = this.playerService.createPlayers(nb)
    this.players.forEach(element => {
      element.forEach(player => {
        console.log(player.dices)
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
    this.playerService.playerBet(this.bet, this.listOfDiceValues, this.computerPredictionResult).subscribe(result => {
      this.result = result[0];
      console.log(this.result)
    })
  }

  computerPrediction(event: any) {
    event.stopPropagation();
    var nbComputers = 0;
    if (this.nbComputers != undefined){
      nbComputers = this.nbComputers +2;
    }
    this.listOfDiceValues = "";
    this.players.forEach(element => {
      element.forEach(player => {
        player.dices.forEach(dice => {
          this.listOfDiceValues += dice.diceValue.toString() + "&"
        })
      })
    }).then(() =>
      this.playerService.computerPrediction(this.listOfDiceValues.slice(0, -1), nbComputers).subscribe(result => {
        this.computerPredictionResult = result.slice(0, -1);
        // @ts-ignore
        this.rules = result.at(-1)[0];
        console.log(this.computerPredictionResult)
    })
    );
  }
}
