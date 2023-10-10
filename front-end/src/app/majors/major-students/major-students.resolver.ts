import { Injectable } from "@angular/core"
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router"
import { Observable } from "rxjs"
import { MajorService } from "../../services/major.service"
import { Student } from "../../models/student.model"

@Injectable({
  providedIn: "root",
})
export class MajorStudentsResolver implements Resolve<Student[]> {
  constructor(private majorService: MajorService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Student[]> {
    return this.majorService.findStudentsFromMajor(route.params["id"])
  }
}
