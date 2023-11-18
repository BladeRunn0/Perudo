import {DiceModel} from "./dice.model";

export interface Player {
  id: bigint;
  firstName: String ;
  lastName: String ;
  score_id: number ;
  active_dice_number: number ;
  dices: DiceModel[] ;
}
