import { Component, OnInit } from '@angular/core';
import {map, Observable} from "rxjs";
import {Player} from "../../models/player.model";
import {ActivatedRoute, Router} from "@angular/router";
import {PlayerService} from "../../services/player.service";

@Component({
  selector: 'epf-player-details',
  templateUrl: './player-details.component.html',
  styleUrls: ['./player-details.component.scss']
})
export class PlayerDetailsComponent implements OnInit {
  player$: Observable<Player> = this._route.data.pipe(map((data) => data["player"]))

  constructor(
    private _route: ActivatedRoute,
    private router: Router,
    private playerService: PlayerService,
  ){}

  addPlayer(player: Player) {
    this.playerService.addPlayer(player).subscribe(() => {
      this.router.navigate(["players"])
    })
  }
  ngOnInit(): void {
  }

}
