import { Injectable } from "@angular/core"
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router"
import { Observable } from "rxjs"
import { StudentService } from "services/student.service"
import { Student } from "models/student.model"
import { Major } from "models/major.model"

@Injectable({
  providedIn: "root",
})
export class StudentDetailsResolver implements Resolve<Student> {
  constructor(private studentService: StudentService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Student> {
    if (route.params["id"] == "new") {
      return new Observable((observer) => observer.next({firstName: "", lastName: "", major: {name: "", description: "", students: []}}))
    }
    return this.studentService.findById(parseInt(route.params["id"], 10))
  }
}
