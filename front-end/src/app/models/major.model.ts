import { Student } from "./student.model"

export interface Major {
  id?: bigint
  name: string
  description: string
  students: Student[]
}
