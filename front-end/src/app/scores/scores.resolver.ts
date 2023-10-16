import { Injectable } from "@angular/core"
import { Resolve } from "@angular/router"
import { Observable } from "rxjs"
import {Score} from "../models/score.model";
import {ScoreService} from "../services/score.service";

@Injectable({
  providedIn: "root",
})
export class ScoresResolver implements Resolve<Score[]> {
  constructor(private scoreService: ScoreService) {
  }

  resolve(): Observable<Score[]> {
    return this.scoreService.findAll()
  }
}
