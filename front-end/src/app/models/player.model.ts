import { Score } from "./score.model"
import {Dice} from "./dice";

export interface Player {
  id: bigint;
  firstName: String ;
  lastName: String ;
  score_id: number ;
  score: Score;
  active_dice_number: number ;
  dices: Dice[] ;
}
