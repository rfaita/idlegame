import { Component, OnInit } from '@angular/core';
import { Hero } from '../model/hero';
import { HeroService } from '../service/hero.service';
import { PlayerService } from '../service/player.service';
import { HeroTypeService } from '../service/herotype.service';
import { HeroType } from '../model/herotype';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
import { SnotifyService } from 'ng-snotify';
import { notificationConfig } from '../utils/helper';
import { PvpService } from '../service/pvp.service';
import { PvpRating } from '../model/pvpRating';


@Component({
  selector: 'app-pvp',
  templateUrl: './pvp.component.html',
  styleUrls: ['./pvp.component.css']
})
export class PvpComponent implements OnInit {

  public player: String;

  public pvpRatings: PvpRating[];
  public pvpRatingsRoll: PvpRating[];


  constructor(
    private playerService: PlayerService,
    private keycloakService: KeycloakService,
    private pvpService: PvpService) { }

  ngOnInit() {

    this.player = this.keycloakService.getUsername();
    this.roll();

  }

  load() {
    this.pvpService.get().subscribe(env => {
      this.pvpRatings = env.data;
    });
  }

  paidRoll() {
    this.pvpService.clearRoll().subscribe(env => {
      this.roll();
    });
  }

  roll() {
    this.pvpService.roll().subscribe(env => {
      this.pvpRatingsRoll = env.data;
      this.load();
    });
  }

  battle(idPvpRating: String) {
    this.pvpService.battle(idPvpRating).subscribe(env => {
      this.roll();
    });
  }

}
