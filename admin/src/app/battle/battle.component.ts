import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Location } from '@angular/common';
import { ActivatedRouteSnapshot } from '@angular/router';
import { HeroType } from '../model/herotype';
import { HeroTypeService } from '../service/herotype.service';
import { HeroService } from '../service/hero.service';
import { Hero } from '../model/hero';
import { PlayerService } from '../service/player.service';
import { BattleService } from '../service/battle.service';
import { Formation } from '../model/formation';
import { BattlePositionedHero } from '../model/battlePositionedHero';
import { BattleHero } from '../model/battleHero';
import { FormationService } from '../service/formation.service';
import { Battle } from '../model/battle';
import { KeycloakProfile } from 'keycloak-js';
import { KeycloakService } from 'keycloak-angular';


@Component({
  selector: 'app-battle',
  templateUrl: './battle.component.html',
  styleUrls: ['./battle.component.css']
})
export class BattleComponent implements OnInit, OnDestroy {


  public playerAttack: String;
  public playerDefense: String;

  public heroesAttack: Hero[];
  public heroesDefense: Hero[];

  public heroTypes: HeroType[];

  public battle: Battle;

  public simulating: Boolean = false;


  constructor(private route: ActivatedRoute,
    private location: Location,
    private heroService: HeroService,
    private heroTypeService: HeroTypeService,
    private playerService: PlayerService,
    private formationService: FormationService,
    private battleService: BattleService,
    private keycloakService: KeycloakService) { }

  ngOnInit() {
    this.playerAttack = this.keycloakService.getUsername();
    this.playerDefense = this.keycloakService.getUsername();
    this.findAll();
  }

  public findAll() {

    this.heroTypeService.findAll().subscribe(env => {
      this.heroTypes = env.data;
      if (this.playerAttack != null) {
        this.findAllByPlayerAttack()
      }

      if (this.playerDefense != null) {
        this.findAllByPlayerDefense()
      }
    });


  }

  public keyDownFindAllByPlayerDefense(event: any) {
    if (event.keyCode == 13) {
      this.findAllByPlayerDefense();
    }
  }

  public findAllByPlayerDefense() {
    this.playerService.findByName(this.playerDefense).subscribe(env => {
      this.heroService.findAllByPlayer(env.data.id).subscribe(env => {
        this.heroesDefense = env.data;
        this.heroesDefense.forEach(hero => {
          let heroType = this.heroTypes.filter(heroType => heroType.id === hero.heroTypeId)[0];
          hero.heroTypeName = heroType.name;
        });
      });
    });
  }


  public keyDownFindAllByPlayerAttack(event: any) {
    if (event.keyCode == 13) {
      this.findAllByPlayerAttack();
    }
  }

  public findAllByPlayerAttack() {
    this.playerService.findByName(this.playerAttack).subscribe(env => {
      this.heroService.findAllByPlayer(env.data.id).subscribe(env => {
        this.heroesAttack = env.data;
        this.heroesAttack.forEach(hero => {
          let heroType = this.heroTypes.filter(heroType => heroType.id === hero.heroTypeId)[0];
          hero.heroTypeName = heroType.name;
        });
      });
    });
  }

  public runSimulation(data: any) {

    this.simulating = true;
    this.battle = null;

    let attFormation: Formation = new Formation();
    attFormation.formationAllocation = "PVP";
    attFormation.heroes = [];

    for (let index in data) {
      if (!!data[index] && index.indexOf("ATT_") > -1) {
        let battleHero: BattleHero = new BattleHero();
        battleHero.id = data[index];

        let battlePositionedHero: BattlePositionedHero = new BattlePositionedHero();
        battlePositionedHero.position = index.replace("ATT_", "");
        battlePositionedHero.hero = battleHero;

        attFormation.heroes.push(battlePositionedHero);
      }
    }

    let defFormation: Formation = new Formation();
    defFormation.formationAllocation = "PVP_DEFENSE";
    defFormation.heroes = [];

    for (let index in data) {
      if (!!data[index] && index.indexOf("DEF_") > -1) {
        let battlePositionedHero: BattlePositionedHero = new BattlePositionedHero();
        battlePositionedHero.position = index.replace("DEF_", "");

        let battleHero: BattleHero = new BattleHero();
        battleHero.id = data[index];

        battlePositionedHero.hero = battleHero;

        defFormation.heroes.push(battlePositionedHero);
      }
    }

    this.formationService.save(attFormation).subscribe(env => {
      attFormation = env.data;
      this.formationService.save(defFormation).subscribe(env => {
        defFormation = env.data;
        this.battleService.doBattle(attFormation.id, defFormation.id).subscribe(env => {
          this.battle = env.data;
          this.simulating = false;
        }, err => this.simulating = false);
      }, err => this.simulating = false);
    }, err => this.simulating = false);

  }

  trackByIndex(index) {
    return index;
  }

  ngOnDestroy() {

  }


}
