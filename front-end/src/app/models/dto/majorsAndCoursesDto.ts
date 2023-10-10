import { Major } from "../major.model"
import { Course } from "../course.model"

export class MajorsAndCoursesDto {
  constructor(majors: Major[], courses: Course[]) {
    this.majors = majors
    this.courses = courses
  }

  majors: Major[]
  courses: Course[]
}
