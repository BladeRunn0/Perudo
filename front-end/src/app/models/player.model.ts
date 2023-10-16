import { Score } from "./score.model"

export interface Player {
  id: bigint;
  firstName: String ;
  lastName: String ;
  scores: Score[];
  score: Score;
}
