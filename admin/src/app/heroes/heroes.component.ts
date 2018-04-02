import { Component, OnInit } from '@angular/core';
import { Hero } from '../model/hero';
import { HeroService } from '../service/hero.service';
import { UserService } from '../service/user.service';
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

  public nickName: String;
  public heroType: String;
  public heroes: Hero[];
  public heroTypes: HeroType[];

  constructor(private heroService: HeroService,
    private heroTypeService: HeroTypeService,
    private userService: UserService,
    private keycloakService: KeycloakService,
    private snotifyService: SnotifyService) { }

  ngOnInit() {

    this.nickName = this.keycloakService.getUsername()
    this.heroTypeService.findAll().subscribe(env => {
      this.heroTypes = env.data;
      this.findAllByUser();
    });


  }

  public customRoll() {
    this.userService.findByNickName(this.nickName).subscribe(env => {
      this.heroService.customRoll(env.data.id, this.heroType, "UNIQUE").subscribe(env => {
        this.findAllByUser();
      });
    });
  }

  public keyDownFindAllByUser(event: any) {
    if (event.keyCode == 13) {
      this.findAllByUser();
    }
  }

  public findAllByUser() {
    this.userService.findByNickName(this.nickName).subscribe(env => {
      this.heroService.findAllByUser(env.data.id).subscribe(env => {
        this.heroes = env.data
        this.heroes.forEach(hero => {
          let heroType = this.heroTypes.filter(heroType => heroType.id === hero.heroTypeId)[0];
          hero.heroTypeName = heroType.name;
        });
      });
    });
  }

  public delete(id: String) {
    this.heroService.delete(id).subscribe(env => {
      this.findAllByUser();
      this.snotifyService.info("Hero deleted.", '', notificationConfig());
      
    });
  }

}
