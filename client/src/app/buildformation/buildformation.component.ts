import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DragulaService } from 'ng2-dragula/ng2-dragula';
import { BattlePositionedHero } from '../model/battlePositionedHero';
import { Hero } from '../model/hero';
import { PositionedHero } from '../model/positionedHero';
import { FormationService } from '../service/formation.service';
import { HeroesService } from '../service/heroes.service';
import { Formation } from '../model/formation';

@Component({
  selector: 'buildformation',
  templateUrl: './buildformation.component.html',
  styleUrls: ['./buildformation.component.css']
})
export class BuildformationComponent implements OnInit {

  public selectHeroes: Hero[];
  public heroes: Hero[];
  public buildFormation: Map<string, PositionedHero>;

  @Output()
  public newFormation: EventEmitter<Formation> = new EventEmitter<Formation>();

  @Input()
  public allocation: string;

  constructor(private heroesService: HeroesService,
    private formationService: FormationService,
    private dragulaService: DragulaService) {

  }

  private emitFormation() {
    let formation: Formation = new Formation();
    formation.formationAllocation = this.allocation;
    formation.heroes = new Array();

    this.buildFormation.forEach(pHero => {
      if (pHero != null) {
        formation.heroes.push(new PositionedHero(new Hero(pHero.hero.id), pHero.battlePosition));
      }
    });
    this.newFormation.emit(formation);

  }

  ngOnInit() {


    this.dragulaService.drop.subscribe((value) => {
      let [el, dragD, dragO] = value.splice(1);

      if (dragD.children.length > 1) {
        if (el === dragD.children[0]) {
          dragO.appendChild(dragD.children[1]);
        } else {
          dragO.appendChild(dragD.children[0]);
        }
      }
      if (dragD.id != null && dragD.id != "") {
        this.buildFormation.set(dragD.id, new BattlePositionedHero(this.heroes.filter(h => h.id === Number.parseInt(el.id))[0], dragD.id));
      }
      if (dragO.id != null && dragO.id != "") {
        if (dragO.children.length > 0) {
          this.buildFormation.set(dragO.id, new BattlePositionedHero(this.heroes.filter(h => h.id === Number.parseInt(dragO.children[0].id))[0], dragO.id));
        } else {
          this.buildFormation.set(dragO.id, null);
        }
      }

      this.emitFormation();

    });

    this.loadFormation();

  }

  private loadFormation() {
    this.formationService
      .getFormationByAllocation(this.allocation)
      .subscribe(
      buildFormation => {
        this.buildFormation = new Map();

        this.buildFormation.set("FRONT_TOP", buildFormation.heroes.filter(h => h.battlePosition == "FRONT_TOP")[0]);
        this.buildFormation.set("FRONT_MIDDLE", buildFormation.heroes.filter(h => h.battlePosition == "FRONT_MIDDLE")[0]);
        this.buildFormation.set("FRONT_BOTTOM", buildFormation.heroes.filter(h => h.battlePosition == "FRONT_BOTTOM")[0]);
        this.buildFormation.set("BACK_TOP", buildFormation.heroes.filter(h => h.battlePosition == "BACK_TOP")[0]);
        this.buildFormation.set("BACK_MIDDLE", buildFormation.heroes.filter(h => h.battlePosition == "BACK_MIDDLE")[0]);
        this.buildFormation.set("BACK_BOTTOM", buildFormation.heroes.filter(h => h.battlePosition == "BACK_BOTTOM")[0]);

        this.emitFormation();
        this.loadHeroes();
      },
      error => {


        this.buildFormation = new Map();

        this.buildFormation.set("FRONT_TOP", null);
        this.buildFormation.set("FRONT_MIDDLE", null);
        this.buildFormation.set("FRONT_BOTTOM", null);
        this.buildFormation.set("BACK_TOP", null);
        this.buildFormation.set("BACK_MIDDLE", null);
        this.buildFormation.set("BACK_BOTTOM", null);

        this.emitFormation();
        this.loadHeroes();
      });
  }

  private loadHeroes() {
    this.heroesService
      .getHeroes()
      .subscribe(
      heroes => {
        this.heroes = heroes
        this.selectHeroes = Object.assign([], heroes);
        this.cleanUpHeroes();
      },
      error => console.log(error));
  }

  private cleanUpHeroes() {
    this.buildFormation.forEach(pHero => {
      this.selectHeroes.forEach((hero, index, data) => {
        if (pHero != null && pHero.hero.id == hero.id) {
          data.splice(index, 1);
        }
      });
    });
  }

}
