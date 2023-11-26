import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'epf-game-screen',
  templateUrl: './game-screen.component.html',
  styleUrls: ['./game-screen.component.scss']
})
export class GameScreenComponent implements OnInit {

  constructor(private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      const selectedValue = +params['selectedValue']; // Récupération de la valeur sélectionnée depuis les paramètres de la route
      console.log('nb ordis sélectionnés et importés : ', selectedValue);
      // Utilisez la valeur sélectionnée comme nécessaire dans votre composant GameScreen
    });
  }

  ngOnInit(): void {
  }

}
