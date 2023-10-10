import { Major } from "../major.model"
import { Student } from "../student.model"

export class MajorStudentsDto {
  major: Major
  students: Student[]

  constructor(major: Major, students: Student[]) {
    this.major = major
    this.students = students
  }
}
