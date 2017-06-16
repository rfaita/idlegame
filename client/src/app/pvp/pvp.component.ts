import { Component, OnInit } from '@angular/core';
import { FormationService } from '../service/formation.service';
import { PvpRoll } from '../model/pvpRoll';
import { Observable } from 'rxjs/Observable';
import { Formation } from '../model/formation';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-pvp',
  templateUrl: './pvp.component.html',
  styleUrls: ['./pvp.component.css']
})
export class PvpComponent implements OnInit {

  public timeUntilNextRoll: number;
  public pvpRoll: PvpRoll;
  public formation: Formation;
  private subscriptionTimer: Subscription;

  constructor(private formationService: FormationService) { }

  private startSubscriptionTimer() {
    if (!!this.subscriptionTimer) {
      this.subscriptionTimer.unsubscribe();
    }
    this.subscriptionTimer = Observable.interval(1000)
      .subscribe(() => {
        this.timeUntilNextRoll = this.pvpRoll.expireDate - Date.now();
        if (this.timeUntilNextRoll <= 0) {
          this.subscriptionTimer.unsubscribe();
        }
      });
  }

  public paidRoll() {
    this.formationService.pvpRollPaid()
      .subscribe(pvpRoll => {
        this.pvpRoll = pvpRoll;
        this.startSubscriptionTimer();

      },
      error => console.log(error));
  }
  public refreshRoll() {
    this.formationService.pvpRoll()
      .subscribe(pvpRoll => {
        this.pvpRoll = pvpRoll;
        this.startSubscriptionTimer();

      },
      error => console.log(error));
  }

  public refresh(formation: Formation) {
    this.formation = formation;
  }

  ngOnInit() {
    this.refreshRoll();
  }
}
