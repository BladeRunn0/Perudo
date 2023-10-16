import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Score} from "../models/score.model";
import {ScoreService} from "../services/score.service";

@Component({
  selector: 'epf-scores',
  templateUrl: './scores.component.html',
  styleUrls: ['./scores.component.scss']
})
export class ScoresComponent implements OnInit {
  scores : Observable<Score[]>
  constructor(private scoreService:ScoreService) {
    this.scores = scoreService.findAll()
  }

  ngOnInit(): void {
  }

}
