import { Component, OnInit } from "@angular/core"
import { map, Observable } from "rxjs"
import { ActivatedRoute } from "@angular/router"
import { MajorStudentsDto } from "../../models/dto/MajorStudentsDto"
import { Student } from "../../models/student.model"

@Component({
  selector: "epf-major-students",
  templateUrl: "./major-students.component.html",
  styleUrls: ["./major-students.component.scss"],
})
export class MajorStudentsComponent implements OnInit {
  studentsFromMajor$: Observable<Student[]> = this._route.data.pipe(map((data) => data["studentsFromMajor"]))

  constructor(private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
  }
}
