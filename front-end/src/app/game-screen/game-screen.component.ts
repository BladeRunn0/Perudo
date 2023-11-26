import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {SharedService} from "../services/shared-service.service";

@Component({
  selector: 'epf-game-screen',
  templateUrl: './game-screen.component.html',
  styleUrls: ['./game-screen.component.scss']
})
export class GameScreenComponent implements OnInit {
  nbComputers: number | undefined;
  constructor(
    private route: ActivatedRoute,
    private sharedService: SharedService
  ) {  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.nbComputers = +params['selectedValue']; // Récupération de la valeur sélectionnée depuis les paramètres de la route
      this.sharedService.setNbComputers(this.nbComputers);
      console.log('nb ordis sélectionnés et importés : ', this.nbComputers);
      // Utilisez la valeur sélectionnée comme nécessaire dans votre composant GameScreen
    });
  }

}
