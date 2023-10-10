import { Component, OnInit } from "@angular/core"
import { map, Observable } from "rxjs"
import { Student } from "models/student.model"
import { ActivatedRoute, Router } from "@angular/router"
import { Course } from "models/course.model"
import { CourseService } from "services/course.service"
import { StudentService } from "services/student.service"
import { Major } from "../../models/major.model"
import { MajorService } from "../../services/major.service"

@Component({
  selector: "epf-student-details",
  templateUrl: "./student-details.component.html",
  styleUrls: ["./student-details.component.scss"],
})
export class StudentDetailsComponent {
  student$: Observable<Student> = this._route.data.pipe(map((data) => data["student"]))
  allMajors$: Observable<Major[]> | undefined
  allCourses$: Observable<Course[]> | undefined
  majorSelectModel: Major | null = null
  courseSelectModel: Course | null = null
  notSelectedCourse: boolean | undefined
  today = new Date(Date.now())

  constructor(
    private _route: ActivatedRoute,
    private courseService: CourseService,
    private studentService: StudentService,
    private majorService: MajorService,
    private router: Router,
  ) {
    this.allMajors$ = this.majorService.findAll()
  }

  courseClick() {
    this.allCourses$ = this.courseService.findAll()
  }

  addCourseToStudent(student: Student) {
    if (this.courseSelectModel != null) {
      this.studentService.addCourseToStudent(student, this.courseSelectModel!!)
    } else {
      this.notSelectedCourse = true
    }
  }

  removeCourseToStudent(student: Student, course: Course) {
    this.studentService.removeCourseToStudent(student, course)
  }

  save(student: Student) {
    const id = this._route.snapshot.params["id"]

    if (this.majorSelectModel !== null) {
      student.major = this.majorSelectModel
    }

    if (id == "new") {
      this.studentService.create(student).subscribe(() => {
        this.router.navigate(["students"])
      })
    } else {
      this.studentService.update(id, student).subscribe(() => {
        this.router.navigate(["students"])
      })
    }
  }

  // because the format of the date doesn't fit date picker
  updateBirthdate($event: any, student: Student) {
    student.birthdate = new Date($event)
  }
}
