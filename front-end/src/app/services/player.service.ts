import { Injectable } from "@angular/core"
import { Observable } from "rxjs"
import { HttpClient } from "@angular/common/http"
import {Player} from "../models/player.model";

@Injectable({
  providedIn: "root",
})
export class PlayerService {
  constructor(private http: HttpClient) {
  }

  private playerUrl = "http://localhost:8080/players"

  findAll(): Observable<Player[]> {
    return this.http.get<Player[]>(this.playerUrl)
  }

  createPlayers(nb: number | undefined): Observable<Player[]> {
    console.log("createPlayers from player.service")
    return this.http.get<Player[]>(`${this.playerUrl}/game/${nb}`)
  }

  deletePlayers(player: Player) {
      return this.http.delete(`${this.playerUrl}/${player.id}`)
  }

  addPlayer(player: Player) {
    return this.http.post(this.playerUrl, player)
  }

  findById(id: number) {
    return this.http.get(`${this.playerUrl}/${id}`)
  }
}
