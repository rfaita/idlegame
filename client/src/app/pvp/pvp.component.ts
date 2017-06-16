import { Component, OnInit } from '@angular/core';
import { FormationService } from '../service/formation.service';
import { PvpRoll } from '../model/pvpRoll';

@Component({
  selector: 'app-pvp',
  templateUrl: './pvp.component.html',
  styleUrls: ['./pvp.component.css']
})
export class PvpComponent implements OnInit {

  public pvpRoll: PvpRoll;

  constructor(private formationService: FormationService) { }

  public refresh() {
    this.formationService.pvpRoll()
      .subscribe(pvpRoll => this.pvpRoll = pvpRoll,
      error => console.log(error));
  }

  ngOnInit() {
    this.refresh();
  }
}
