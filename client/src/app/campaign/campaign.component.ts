import { Component, OnInit } from '@angular/core';
import { FormationService } from '../service/formation.service';
import { Formation } from '../model/formation';
import { Hero } from '../model/hero';
import { BattleService } from '../service/battle.service';
import { HeroesService } from '../service/heroes.service';
import { PositionedHero } from '../model/positionedHero';
import { DragulaService } from 'ng2-dragula/ng2-dragula';

@Component({
  selector: 'app-campaign',
  templateUrl: './campaign.component.html',
  styleUrls: ['./campaign.component.css']
})
export class CampaignComponent implements OnInit {

  public nextLevelFormationPve: Formation;
  public formationPve: Formation;
  public selectHeroes: Hero[];
  public heroes: Hero[];
  public formationPveTmp: Map<string, PositionedHero> = new Map();

  constructor(private formationService: FormationService,
    private battleService: BattleService,
    private heroesService: HeroesService,
    private dragulaService: DragulaService) {

    this.formationPveTmp.set("FRONT_TOP", null);
    this.formationPveTmp.set("FRONT_MIDDLE", null);
    this.formationPveTmp.set("FRONT_BOTTOM", null);
    this.formationPveTmp.set("BACK_TOP", null);
    this.formationPveTmp.set("BACK_MIDDLE", null);
    this.formationPveTmp.set("BACK_BOTTOM", null);

    dragulaService.drag.subscribe((value) => {
      
    });
    dragulaService.drop.subscribe((value) => {
      let [el, dragD, dragO] = value.splice(1);

      if (dragD.children.length > 1) {
        if (el === dragD.children[0]) {
          dragO.appendChild(dragD.children[1]);
        } else {
          dragO.appendChild(dragD.children[0]);
        }
      }

      

    });
    dragulaService.over.subscribe((value) => {
      

    });
    dragulaService.out.subscribe((value) => {
      

    });

  }

  public fight() {
    console.log(this.formationPveTmp);

    /*this.battleService.doBattlePve()
      .subscribe(
      battleRetorno => {
        alert("O time vencedor é: " + battleRetorno.winner);
        if (battleRetorno.winner === "ATTACK") {
          this.loadNextLevelFormationPve();
        }
        console.log(battleRetorno);
      },
      error => console.log(error)
      );*/
  }

  public loadNextLevelFormationPve() {
    this.formationService.getNextLevelFormationPve()
      .subscribe(
      nextLevelFormationPve => {
        this.nextLevelFormationPve = nextLevelFormationPve;
      },
      error => console.log(error)
      );
  }

  ngOnInit() {
    this.loadNextLevelFormationPve();



    this.formationService.getFormationByAllocation("PVE")
      .subscribe(
      formationPve => {
        this.formationPve = formationPve;
        console.log(this.formationPve);
        this.formationPve.positionedHeroes.forEach(hero => {
          this.formationPveTmp.set(hero.battlePosition, hero);
        });
        console.log(this.formationPveTmp);
        this.loadHeroes();
      },
      error => console.log(error)
      );


  }

  private loadHeroes() {
    this.heroesService.getHeroes()
      .subscribe(
      heroes => {
        this.heroes = heroes
        this.selectHeroes = heroes;
        this.cleanUpHeroes();
      },
      error => console.log(error));
  }


  private cleanUpHeroes() {
    this.selectHeroes.forEach((hero, index, data) => {

      this.formationPveTmp.forEach(pHero => {
        if (pHero != null && pHero.hero.id == hero.id) {
          data.splice(index, 1);
        }
      })


    });
  }

}
