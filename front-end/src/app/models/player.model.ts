import {DiceModel} from "./dice.model";

export interface Player {
  id: bigint;
  firstName: String ;
  lastName: String ;
  score_id: number ;
  activeDiceNumber: number ;
  dices: DiceModel[] ;
}
