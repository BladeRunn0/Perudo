import { Component, OnInit } from "@angular/core"
import { Link } from "models/links.model"

@Component({
  selector: "epf-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.scss"],
})
export class NavbarComponent {
  links: Link[] = []

  constructor() {
    // this.links.push({ name: "Players", href: "players" })
    // this.links.push({ name: "Scores", href: "scores" })
    this.links.push({ name: "Règles", href: "rules" })
  }
}
