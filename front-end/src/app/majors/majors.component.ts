import { Component } from "@angular/core"
import { map, Observable } from "rxjs"
import { Major } from "models/major.model"
import { ActivatedRoute } from "@angular/router"

@Component({
  selector: "epf-majors",
  templateUrl: "./majors.component.html",
  styleUrls: ["./majors.component.scss"],
})
export class MajorsComponent {
  majors$: Observable<Major[]> = this._route.data.pipe(map((data) => data["majors"]))

  constructor(private _route: ActivatedRoute) {
  }
}
