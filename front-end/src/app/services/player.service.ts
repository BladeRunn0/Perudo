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

  computerPrediction(listOfDiceValues: String): Observable<string[][]> {
    return this.http.get<string[][]>(`${this.playerUrl}/game/computer-predictions/${listOfDiceValues}`)
  }

  playerBet(betDice: string[], countDices: string, computerPredictionResult: string[][]):  Observable<String>{
    const betDiceString = betDice.join('&');
    var serializedPrediction: string = "";
    for (var i = 0; i<computerPredictionResult.length; i++){
      for (let j = 0; j<computerPredictionResult[i].length; j++){
        if (computerPredictionResult[i][j] == "DOUBT"){
          serializedPrediction += "DOUBT"
          break;
        }
        else if (j == 0){
          serializedPrediction += computerPredictionResult[i][j] + "&"
        }
        else {
          serializedPrediction += computerPredictionResult[i][j]
        }
      }
      serializedPrediction += "-"
    }
    serializedPrediction = serializedPrediction.slice(0, -1)
    let test = this.http.get<String>(`${this.playerUrl}/game/playerBet/${betDiceString}/${countDices}/${serializedPrediction}`)
    return this.http.get<String>(`${this.playerUrl}/game/playerBet/${betDiceString}/${countDices}/${serializedPrediction}`)
  }
}
