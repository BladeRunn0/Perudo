import { NgModule } from "@angular/core"
import { RouterModule, Routes } from "@angular/router"
import { HomeComponent } from "home/home.component"
import { StudentsComponent } from "students/students.component"
import { StudentsResolver } from "students/students.resolver"
import { StudentDetailsComponent } from "students/student-details/student-details.component"
import { StudentDetailsResolver } from "students/student-details/student-details.resolver"
import { MajorsComponent } from "majors/majors.component"
import { MajorsResolver } from "majors/majors.resolver"
import { MajorStudentsResolver } from "majors/major-students/major-students.resolver"
import { MajorStudentsComponent } from "majors/major-students/major-students.component"
import {PlayersComponent} from "./players/players.component";
import {PlayersResolver} from "./players/players.resolver";
import {ScoresComponent} from "./scores/scores.component";
import {ScoresResolver} from "./scores/scores.resolver";
import {RulesComponent} from "./rules/rules.component";
import {GameScreenComponent} from "./game-screen/game-screen.component";

const routes: Routes = [
  { path: "", component: HomeComponent },
  {
    path: "students",
    component: StudentsComponent,
    resolve: {
      students: StudentsResolver,
    },
  },
  {
    path: "student-details/:id",
    component: StudentDetailsComponent,
    resolve: {
      student: StudentDetailsResolver,
    },
  },
  {
    path: "majors",
    component: MajorsComponent,
    resolve: {
      majors: MajorsResolver,
    },
  },
  {
    path: "players",
    component: PlayersComponent,
    resolve: {
      players: PlayersResolver,
    },
  },
  {
    path: 'game_screen/:selectedValue',
    component: GameScreenComponent,
  },
  {
    path: "scores",
    component: ScoresComponent,
    resolve: {
      scores: ScoresResolver,
    },
  },
  {
    path: "rules",
    component: RulesComponent,
  },
  {
    path: "major-students/:id",
    component: MajorStudentsComponent,
    resolve: {
      studentsFromMajor: MajorStudentsResolver,
    },
  },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
