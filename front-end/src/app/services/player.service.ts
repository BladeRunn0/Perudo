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
}
