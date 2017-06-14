import { Component, OnInit } from '@angular/core';
import { FormationService } from '../service/formation.service';
import { Formation } from '../model/formation';
import { Hero } from '../model/hero';
import { BattleService } from '../service/battle.service';
import { HeroesService } from '../service/heroes.service';
import { PositionedHero } from '../model/positionedHero';
import { DragulaService } from 'ng2-dragula/ng2-dragula';
import { BattlePositionedHero } from '../model/battlePositionedHero';

@Component({
  selector: 'app-campaign',
  templateUrl: './campaign.component.html',
  styleUrls: ['./campaign.component.css']
})
export class CampaignComponent implements OnInit {

  public nextLevelFormationPve: Formation;
  public selectHeroes: Hero[];
  public heroes: Hero[];
  public formationPve: Map<string, PositionedHero>;

  constructor(private formationService: FormationService,
    private battleService: BattleService,
    private heroesService: HeroesService,
    private dragulaService: DragulaService) {

    dragulaService.drop.subscribe((value) => {
      let [el, dragD, dragO] = value.splice(1);

      if (dragD.children.length > 1) {
        if (el === dragD.children[0]) {
          dragO.appendChild(dragD.children[1]);
        } else {
          dragO.appendChild(dragD.children[0]);
        }
      }
      if (dragD.id != null && dragD.id != "") {
        this.formationPve.set(dragD.id, new BattlePositionedHero(this.heroes.filter(h => h.id === Number.parseInt(el.id))[0], dragD.id));
      }
      if (dragO.id != null && dragO.id != "") {
        if (dragO.children.length > 0) {
          this.formationPve.set(dragO.id, new BattlePositionedHero(this.heroes.filter(h => h.id === Number.parseInt(dragO.children[0].id))[0], dragO.id));
        } else {
          this.formationPve.set(dragO.id, null);
        }
      }
    });

  }

  public fight() {

    let formation: Formation = new Formation();
    formation.formationAllocation = "PVE";
    formation.heroes = new Array();

    this.formationPve.forEach(pHero => {
      if (pHero != null) {
        formation.heroes.push(new PositionedHero(new Hero(pHero.hero.id), pHero.battlePosition));
      }
    });

    console.log(formation);

    this.formationService.putFormation(formation)
      .subscribe(
      ret => {
        this.doBattlePve();
      }, error => console.log(error)
      );
  }

  private doBattlePve() {
    this.battleService.doBattlePve()
      .subscribe(
      battleRetorno => {
        alert("O time vencedor é: " + battleRetorno.winner);
        if (battleRetorno.winner === "ATTACK") {
          this.loadNextLevelFormationPve();
        }
        console.log(battleRetorno);
      },
      error => console.log(error)
      );
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
        this.formationPve = new Map();

        this.formationPve.set("FRONT_TOP", formationPve.heroes.filter(h => h.battlePosition == "FRONT_TOP")[0]);
        this.formationPve.set("FRONT_MIDDLE", formationPve.heroes.filter(h => h.battlePosition == "FRONT_MIDDLE")[0]);
        this.formationPve.set("FRONT_BOTTOM", formationPve.heroes.filter(h => h.battlePosition == "FRONT_BOTTOM")[0]);
        this.formationPve.set("BACK_TOP", formationPve.heroes.filter(h => h.battlePosition == "BACK_TOP")[0]);
        this.formationPve.set("BACK_MIDDLE", formationPve.heroes.filter(h => h.battlePosition == "BACK_MIDDLE")[0]);
        this.formationPve.set("BACK_BOTTOM", formationPve.heroes.filter(h => h.battlePosition == "BACK_BOTTOM")[0]);

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
        this.selectHeroes = Object.assign([], heroes);
        this.cleanUpHeroes();
      },
      error => console.log(error));
  }


  private cleanUpHeroes() {
    this.formationPve.forEach(pHero => {
      this.selectHeroes.forEach((hero, index, data) => {
        if (pHero != null && pHero.hero.id == hero.id) {
          data.splice(index, 1);
        }
      });
    });
  }

}
