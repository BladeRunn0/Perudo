import { Injectable } from "@angular/core"
import { Observable } from "rxjs"
import { HttpClient } from "@angular/common/http"
import {Player} from "../models/player.model";
import {Score} from "../models/score.model";

@Injectable({
  providedIn: "root",
})
export class ScoreService {
  constructor(private http: HttpClient) {
  }

  private scoreUrl = "http://localhost:8080/scores"

  findAll(): Observable<Score[]> {
    return this.http.get<Score[]>(this.scoreUrl)
  }
}
