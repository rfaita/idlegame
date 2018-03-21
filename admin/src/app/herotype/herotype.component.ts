import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Location } from '@angular/common';
import { HeroTypeService } from '../service/herotype.service';
import { HeroType } from '../model/herotype';
import { ActivatedRouteSnapshot } from '@angular/router';
import { HeroTypeTypesService } from '../service/herotypetypes.service';
import { HeroTypeTypes } from '../model/herotypetypes';
import { ActionEffectTypesService } from '../service/actioneffecttypes.service';
import { ActionEffectTypes } from '../model/actionEffectTypes';
import { Action } from '../model/action';
import { ActionEffect } from '../model/actionEffect';
import { clone, notificationConfig } from '../utils/helper';
import { SnotifyService } from 'ng-snotify';
import { Defense } from '../model/defense';

@Component({
  selector: 'app-herotype',
  templateUrl: './herotype.component.html',
  styleUrls: ['./herotype.component.css']
})
export class HeroTypeComponent implements OnInit, OnDestroy {

  public heroType: HeroType;
  public heroTypeTypes: HeroTypeTypes;
  public actionEffectTypes: ActionEffectTypes;

  public defaultActionFormationPositionType: string = "";
  public specialActionFormationPositionType: string = "";


  private sub: any;

  constructor(private route: ActivatedRoute,
    private location: Location,
    private heroTypeService: HeroTypeService,
    private heroTypeTypesService: HeroTypeTypesService,
    private actionEffectTypesService: ActionEffectTypesService,
    private snotifyService: SnotifyService) { }

  ngOnInit() {

    this.findById();

  }

  public findById() {

    this.sub = this.route.params.subscribe(params => {
      let id: string = params['id'];
      this.heroTypeTypesService.get().subscribe(env => {
        this.heroTypeTypes = env.data;
        this.actionEffectTypesService.get().subscribe(env => {
          this.actionEffectTypes = env.data;
          if (id !== "new") {
            this.heroTypeService.findById(id).subscribe(env => {
              this.heroType = env.data;
            });
          } else {
            this.heroType = new HeroType();
            this.heroType.minBaseDefenses = [];
            this.heroType.maxBaseDefenses = [];
            this.heroType.minMaxLevelDefenses = [];
            this.heroType.maxMaxLevelDefenses = [];

            for (let item in this.heroTypeTypes.defenseTypes) {
              this.heroType.minBaseDefenses.push(new Defense(this.heroTypeTypes.defenseTypes[item], 0));
              this.heroType.maxBaseDefenses.push(new Defense(this.heroTypeTypes.defenseTypes[item], 0));
              this.heroType.minMaxLevelDefenses.push(new Defense(this.heroTypeTypes.defenseTypes[item], 0));
              this.heroType.maxMaxLevelDefenses.push(new Defense(this.heroTypeTypes.defenseTypes[item], 0));
            }
          }

        });
      });
    });

  }

  public save(data: any) {
    this.heroTypeService.save(this.heroType).subscribe(env => {
      this.heroType = env.data;
      this.snotifyService.info("Hero Type saved.", '', notificationConfig());
    });
  }

  public addDefaultAction() {
    if (this.defaultActionFormationPositionType != "") {
      if (this.heroType.defaultActions == null) {
        this.heroType.defaultActions = [];
      }
      if (this.defaultAction(this.heroType, this.defaultActionFormationPositionType) == null) {
        this.heroType.defaultActions.push(new Action(this.defaultActionFormationPositionType));
      }
      this.defaultActionFormationPositionType = "";
    }
  }

  public removeDefaultAction(i: number) {
    this.heroType.defaultActions.splice(i, 1);
  }

  public addSpecialAction() {
    if (this.specialActionFormationPositionType != "") {
      if (this.heroType.specialActions == null) {
        this.heroType.specialActions = [];
      }
      if (this.specialAction(this.heroType, this.specialActionFormationPositionType) == null) {
        this.heroType.specialActions.push(new Action(this.specialActionFormationPositionType));
      }
      this.specialActionFormationPositionType = "";
    }
  }

  public removeSpecialAction(i: number) {
    this.heroType.specialActions.splice(i, 1);
  }

  public addSecundaryActionEffectDefaultAction(action: Action) {
    if (action.secundaryActionsEffects == null) {
      action.secundaryActionsEffects = [];
    }
    action.secundaryActionsEffects.push(new ActionEffect());
  }

  public changeSecundaryActionEffectDefaultAction(action: Action, event: ActionEffect, i: number) {
    if (event != null) {
      action.secundaryActionsEffects[i] = event;
    } else {
      action.secundaryActionsEffects.splice(i, 1);
    }
  }

  public addSecundaryActionEffectSpecialAction(action: Action) {
    if (action.secundaryActionsEffects == null) {
      action.secundaryActionsEffects = [];
    }
    action.secundaryActionsEffects.push(new ActionEffect());
  }

  public changeSecundaryActionEffectSpecialAction(action: Action, event: ActionEffect, i: number) {
    if (event != null) {
      action.secundaryActionsEffects[i] = event;
    } else {
      action.secundaryActionsEffects.splice(i, 1);
    }
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }


  private defaultAction(heroType: HeroType, fpt: String): Action {
    let ret: Action[] = heroType.defaultActions.filter(a => a.formationPositionType == fpt);
    if (ret.length > 0) {
      return ret[0];
    } else {
      return null;
    }

  }

  private specialAction(heroType: HeroType, fpt: String): Action {
    let ret: Action[] = heroType.specialActions.filter(a => a.formationPositionType == fpt);
    if (ret.length > 0) {
      return ret[0];
    } else {
      return null;
    }

  }

}
