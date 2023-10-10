import { Injectable } from "@angular/core"
import { Observable } from "rxjs"
import { Course } from "models/course.model"
import { HttpClient } from "@angular/common/http"

@Injectable({
  providedIn: "root",
})
export class CourseService {
  constructor(private http: HttpClient) {
  }

  private coursesUrl = "http://localhost:8080/courses"

  findAll(): Observable<Course[]> {
    return this.http.get<Course[]>(this.coursesUrl)
  }
}
