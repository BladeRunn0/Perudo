import { Component, OnInit, ViewChild } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Router } from '@angular/router';

@Component({
  selector: "epf-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  @ViewChild('playForm') playForm!: NgForm;
  @ViewChild('playerName') playerName!: NgForm;
  @ViewChild('numberOfComputers') numberOfComputers!: NgForm;
  @ViewChild('playButton') playButton!: NgForm;
  playerNameValue: string = '';


  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  onSubmit(nbComputers: string) {
    this.router.navigate(['/game_screen', nbComputers ]);
  }

  validatePlayerName() {
    const playerNameInput = document.getElementById('playerName');
    const errorMessage = document.getElementById('errorMessage');

    if (playerNameInput?.textContent?.trim() === '') {
      return false;
    } else {
      // Si le nom est rempli, effectuez l'action associée au clic du bouton
      return true;
    }
  }


}
