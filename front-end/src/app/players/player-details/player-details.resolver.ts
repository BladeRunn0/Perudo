import { Injectable } from "@angular/core"
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router"
import { Observable } from "rxjs"
import {Player} from "../../models/player.model";
import {PlayerService} from "../../services/player.service";
@Injectable({
  providedIn: "root",
})
export class PlayerDetailsResolver implements Resolve<Player> {
  constructor(private playerService: PlayerService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Player> {
    if (route.params["id"] == "new") {
      // @ts-ignore
      return new Observable((observer) => observer.next({
        active_dice_number: 0,
        dices: [],
        id: 0n,
        score_id: 0,
        firstName: "", lastName: ""}))
    }
    // @ts-ignore
    return this.playerService.findById(parseInt(route.params["id"], 10))
  }
}
