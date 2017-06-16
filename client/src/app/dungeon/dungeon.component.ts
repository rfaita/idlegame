import { Component, OnInit } from '@angular/core';
import { Formation } from '../model/formation';
import { FormationService } from '../service/formation.service';
import { BattleService } from '../service/battle.service';

@Component({
  selector: 'app-dungeon',
  templateUrl: './dungeon.component.html',
  styleUrls: ['./dungeon.component.css']
})
export class DungeonComponent implements OnInit {

  public nextLevelFormationDungeon: Formation;
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
        this.doBattleDungeon();
      }, error => console.log(error)
      );
  }

  private doBattleDungeon() {
    this.battleService.doBattleDungeon()
      .subscribe(
      battleRetorno => {
        alert("O time vencedor é: " + battleRetorno.winner);
        if (battleRetorno.winner === "ATTACK") {
          this.loadNextLevelFormationDungeon();
        }
        console.log(battleRetorno);
      },
      error => console.log(error)
      );
  }

  public loadNextLevelFormationDungeon() {
    this.formationService.getNextLevelFormationDungeon()
      .subscribe(
      nextLevelFormationDungeon => {
        this.nextLevelFormationDungeon = nextLevelFormationDungeon;
      },
      error => console.log(error)
      );
  }

  ngOnInit() {
    this.loadNextLevelFormationDungeon();

  }

}
