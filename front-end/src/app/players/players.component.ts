import {ChangeDetectorRef, Component} from '@angular/core';
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
  players: Observable<Player[]> = new Observable<Player[]>()
  nbComputers: number | undefined;
  computerPredictionResult: string[][] = [[""]];
  listOfDiceValues = "";
  bet: string[] = ["",""];
  result: string = "";
  rules: string = "";
  playerDices: number[] = [];
  showFields: boolean = false;
  showDices: boolean = false;
  showDudo: boolean = false;
  showActionButtons: boolean = true;

  constructor(
    private _route: ActivatedRoute,
    private playerService: PlayerService,
    private router: Router,
    private sharedService: SharedService,
    private changeDetectorRef: ChangeDetectorRef
  ) {
    this.players = playerService.findAll()
  }

  ngOnInit(): void {
    this.sharedService.getNbComputers().subscribe(value => {
      this.nbComputers = value;
      this.createPlayers(this.nbComputers+1);
    });
    this.players.subscribe(players => {
      if (players.length > 0) {
        const lastPlayer = players[players.length - 1];
        this.playerDices = lastPlayer.dices.map(dice => dice.diceValue);
      }
    });
  }

  toggleFieldsVisibilityBets(): void {
    this.showFields = !this.showFields;
  }

  toggleFieldsVisibilityDices(): void {
    this.showDices = !this.showDices;
  }

  toggleFieldsVisibilityDudo(): void {
    this.showDudo = !this.showDudo;
  }

  toggleFieldsVisibilityActionButtons(): void {
    this.showActionButtons = !this.showActionButtons;
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

  loadPlayers(): void {
    this.players.subscribe(() => {
      this.changeDetectorRef.detectChanges(); // Force la détection des changements pour mettre à jour la vue
    });
  }

  createPlayers(nb: number | undefined) {
    this.loadPlayers();
    this.listOfDiceValues = "";
    this.computerPredictionResult = [[""]];
    this.players = new Observable<Player[]>()
    this.players = this.playerService.createPlayers(nb);
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
    console.log("pari : ", this.bet);
    this.playerService.playerBet(this.bet, this.listOfDiceValues, this.computerPredictionResult).subscribe(result => {
      this.result = result[0];
      console.log(this.result)
    });
    this.toggleFieldsVisibilityDices();
  }

  computerPrediction(event: any) {
    event.stopPropagation();
    var nbComputers = 0;
    if (this.nbComputers != undefined){
      nbComputers = this.nbComputers +1;
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

        for (let i = 0; i < result.length; i++){
          this.computerPredictionResult[i] = result[i]
          if (result[i][0] == "DOUBT"){
            this.toggleFieldsVisibilityDudo();
            this.toggleFieldsVisibilityActionButtons();
            break
          }
        }

        // this.computerPredictionResult = result.slice(0, -1);
        // @ts-ignore
        this.rules = result.at(-1)[0];
        console.log(this.computerPredictionResult)
    })
    );
  }
}
