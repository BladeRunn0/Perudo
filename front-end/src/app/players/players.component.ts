import { Component } from '@angular/core';
import {PlayerService} from "../services/player.service";
import {map, Observable} from "rxjs";
import {Player} from "../models/player.model";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'epf-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.scss']
})
export class PlayersComponent{
  players: Observable<Player[]>

  constructor(private _route: ActivatedRoute, private playerService: PlayerService, private router: Router,) {
    this.players = playerService.findAll()
  }

  refreshPage(url: string) {
    this.router.navigateByUrl('/refresh', { skipLocationChange: false }).then(() => {
      this.router.navigate([url]);
    });
  }

  playGame(event: any){
    event.stopPropagation()
    console.log("playGame from player.component")
    this.playerService.playGame().subscribe(() => this.router.navigate(["players"]))
  }

  // addPlayer(event: any, player: Player){
  //   event.stopPropagation()
  //   this.playerService.addPlayer(player).subscribe(() => this.router.navigate(["players"]))
  // }

  deletePlayer(event: any, player: Player) {
    event.stopPropagation();
    this.playerService.deletePlayers(player).subscribe(() => this.router.navigate(["players"], {skipLocationChange: true}));
    //this.refreshPage("game-screen");
  }
}
