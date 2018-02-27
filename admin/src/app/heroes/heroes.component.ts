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


@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  public player: String;
  public heroType: String;
  public heroes: Hero[];
  public heroTypes: HeroType[];

  constructor(private heroService: HeroService,
    private heroTypeService: HeroTypeService,
    private playerService: PlayerService,
    private keycloakService: KeycloakService,
    private snotifyService: SnotifyService) { }

  ngOnInit() {

    this.player = this.keycloakService.getUsername()
    this.heroTypeService.findAll().subscribe(env => {
      this.heroTypes = env.data;
      this.findAllByPlayer();
    });


  }

  public customRoll() {
    this.playerService.findByName(this.player).subscribe(env => {
      this.heroService.customRoll(env.data.id, this.heroType, "UNIQUE").subscribe(env => {
        this.findAllByPlayer();
      });
    });
  }

  public keyDownFindAllByPlayer(event: any) {
    if (event.keyCode == 13) {
      this.findAllByPlayer();
    }
  }

  public findAllByPlayer() {
    this.playerService.findByName(this.player).subscribe(env => {
      this.heroService.findAllByPlayer(env.data.id).subscribe(env => {
        this.heroes = env.data
        this.heroes.forEach(hero => {
          let heroType = this.heroTypes.filter(heroType => heroType.id === hero.heroType)[0];
          hero.heroTypeName = heroType.name;
        });
      });
    });
  }

  public delete(id: String) {
    this.heroService.delete(id).subscribe(env => {
      this.findAllByPlayer();
      this.snotifyService.info("Hero deleted.", '', notificationConfig());
      
    });
  }

}
