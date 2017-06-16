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
  public formation: Formation;

  constructor(private formationService: FormationService,
    private battleService: BattleService) {
  }

  public refresh(formation: Formation) {
    this.formation = formation;
  }

  public fight() {
    this.formationService.putFormation(this.formation)
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

  }

}
