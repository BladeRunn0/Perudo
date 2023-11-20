import { Injectable } from "@angular/core"
import { Resolve } from "@angular/router"
import { Observable } from "rxjs"
import {Player} from "../models/player.model";
import {PlayerService} from "../services/player.service";

@Injectable({
  providedIn: "root",
})
export class PlayersResolver implements Resolve<Player[]> {
  constructor(private playerService: PlayerService) {
  }

  resolve(): Observable<Player[]> {
    return this.playerService.findAll()
  }
}
