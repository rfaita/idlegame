import { Component, OnInit } from '@angular/core';
import { FormationService } from '../service/formation.service';
import { Formation } from '../model/formation';
import { BattleService } from '../service/battle.service';

@Component({
  selector: 'app-campaign',
  templateUrl: './campaign.component.html',
  styleUrls: ['./campaign.component.css']
})
export class CampaignComponent implements OnInit {

  public nextLevelFormationPve: Formation;
  public formationPve: Formation;

  constructor(private formationService: FormationService,
    private battleService: BattleService) { }

  public fight() {
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
        this.formationPve = formationPve;
      },
      error => console.log(error)
      );
  }

}
