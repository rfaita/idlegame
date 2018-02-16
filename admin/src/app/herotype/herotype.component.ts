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
import { clone, showNotification } from '../utils/helper';

@Component({
  selector: 'app-herotype',
  templateUrl: './herotype.component.html',
  styleUrls: ['./herotype.component.css']
})
export class HeroTypeComponent implements OnInit, OnDestroy {

  public heroType: HeroType;
  public heroTypeTypes: HeroTypeTypes;
  public actionEffectTypes: ActionEffectTypes;

  private defaultActionBackUp: Action;
  private specialActionBackUp: Action;

  private sub: any;

  constructor(private route: ActivatedRoute,
    private location: Location,
    private heroTypeService: HeroTypeService,
    private heroTypeTypesService: HeroTypeTypesService,
    private actionEffectTypesService: ActionEffectTypesService) { }

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
            this.heroTypeService.findById(id).subscribe(env => { this.heroType = env.data });
          } else {
            this.heroType = new HeroType();
          }
        });
      });
    });

  }

  public save(data: any) {
    data.id = this.heroType.id;
    data.defaultAction = this.heroType.defaultAction;
    data.specialAction = this.heroType.specialAction;
    this.heroTypeService.save(data).subscribe(env => {
      this.heroType = env.data;
      showNotification("info", "Hero Type saved.");
    });
  }


  public changeHaveDefaultAction() {
    if (this.defaultActionBackUp == null) {
      this.defaultActionBackUp = clone(this.heroType.defaultAction);
    }

    if (this.heroType.defaultAction == null) {
      this.heroType.defaultAction = (this.defaultActionBackUp != null) ? this.defaultActionBackUp : new Action();
    } else {
      this.heroType.defaultAction = null;
    }
  }

  public addSecundaryActionEffectDefaultAction() {
    if (this.heroType.defaultAction.secundaryActionsEffects == null) {
      this.heroType.defaultAction.secundaryActionsEffects = [];
    }
    this.heroType.defaultAction.secundaryActionsEffects.push(new ActionEffect());
  }

  public changeSecundaryActionEffectDefaultAction(event: ActionEffect, i: number) {
    if (event != null) {
      this.heroType.defaultAction.secundaryActionsEffects[i] = event;
    } else {
      this.heroType.defaultAction.secundaryActionsEffects.splice(i, 1);
    }
  }

  public changeHaveSpecialAction() {
    if (this.specialActionBackUp == null) {
      this.specialActionBackUp = clone(this.heroType.specialAction);
    }

    if (this.heroType.specialAction == null) {
      this.heroType.specialAction = (this.specialActionBackUp != null) ? this.specialActionBackUp : new Action();
    } else {
      this.heroType.specialAction = null;
    }
  }

  public addSecundaryActionEffectSpecialAction() {
    if (this.heroType.specialAction.secundaryActionsEffects == null) {
      this.heroType.specialAction.secundaryActionsEffects = [];
    }
    this.heroType.specialAction.secundaryActionsEffects.push(new ActionEffect());
  }

  public changeSecundaryActionEffectSpecialAction(event: ActionEffect, i: number) {
    if (event != null) {
      this.heroType.specialAction.secundaryActionsEffects[i] = event;
    } else {
      this.heroType.specialAction.secundaryActionsEffects.splice(i, 1);
    }
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }


}
