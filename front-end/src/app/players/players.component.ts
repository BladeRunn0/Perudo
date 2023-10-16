import { Component, OnInit } from '@angular/core';
import {PlayerService} from "../services/player.service";
import {Observable} from "rxjs";
import {Player} from "../models/player.model";

@Component({
  selector: 'epf-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.scss']
})
export class PlayersComponent implements OnInit {
  players : Observable<Player[]>
  constructor(private playerService:PlayerService) {
    this.players = playerService.findAll()
  }

  ngOnInit(): void {
  }

}
