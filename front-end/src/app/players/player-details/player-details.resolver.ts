import { Injectable } from "@angular/core"
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router"
import { Observable } from "rxjs"
import {Player} from "../../models/player.model";
import {Score} from "../../models/score.model";
import {PlayerService} from "../../services/player.service";
@Injectable({
  providedIn: "root",
})
export class PlayerDetailsResolver implements Resolve<Player> {
  constructor(private playerService: PlayerService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Player> {
    if (route.params["id"] == "new") {
      console.log("resolver")
      return new Observable((observer) => observer.next({
        score_id: 0,
        active_dice_number: 0,
        dices: [],
        id: 0n,
        firstName: "",
        lastName: ""}))
    }
    // @ts-ignore
    return this.playerService.findById(parseInt(route.params["id"], 10))
  }
}
