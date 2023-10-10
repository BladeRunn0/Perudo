import { Injectable } from "@angular/core"
import { Resolve } from "@angular/router"
import { Observable } from "rxjs"
import { Student } from "models/student.model"
import { StudentService } from "services/student.service"

@Injectable({
  providedIn: "root",
})
export class StudentsResolver implements Resolve<Student[]> {
  constructor(private studentService: StudentService) {
  }

  resolve(): Observable<Student[]> {
    return this.studentService.findAll()
  }
}
