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
import { Hero } from '../model/hero';
import { HeroService } from '../service/hero.service';
import { HeroTypesService } from '../service/herotypes.service';
import { HeroTypes } from '../model/herotypes';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit, OnDestroy {

  public hero: Hero;
  public heroTypes: HeroTypes;
  public heroTypesData: HeroType[];

  private sub: any;

  constructor(private route: ActivatedRoute,
    private location: Location,
    private heroService: HeroService,
    private heroTypesService: HeroTypesService,
    private heroTypeService: HeroTypeService) { }

  ngOnInit() {

    this.findById();

  }

  public findById() {

    this.sub = this.route.params.subscribe(params => {
      let id: string = params['id'];
      this.heroTypeService.findAll().subscribe(env => {
        this.heroTypesData = env.data;
        this.heroTypesService.get().subscribe(env => {
          this.heroTypes = env.data;
          if (id !== "new") {

            this.heroService.findById(id).subscribe(env => { this.hero = env.data; console.log(env.data); });
          } else {
            this.hero = new Hero();
          }
        });
      });
    });

  }

  public save(data: any) {
    data.id = this.hero.id;

    /*this.heroTypeService.save(data).subscribe(env => {
      this.heroType = env.data;
      showNotification("info", "Hero Type saved.");
    });*/
  }


  ngOnDestroy() {
    this.sub.unsubscribe();
  }


}
