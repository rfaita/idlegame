import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';
import { ActivatedRoute } from "@angular/router";
import { Location } from '@angular/common';
import { Hero } from '../model/hero';
import { HeroTypes } from '../model/herotypes';
import { HeroType } from '../model/herotype';
import { HeroService } from '../service/hero.service';
import { HeroTypesService } from '../service/herotypes.service';
import { HeroTypeService } from '../service/herotype.service';
import { SnotifyService } from 'ng-snotify';
import { notificationConfig } from '../utils/helper';

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
    private heroTypeService: HeroTypeService,
    private snotifyService: SnotifyService) { }

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
            this.heroService.findById(id).subscribe(env => { this.hero = env.data; });
          } else {
            this.hero = new Hero();
          }
        });
      });
    });

  }

  public save(data: any) {
    data.id = this.hero.id;

    this.heroService.save(data).subscribe(env => {
      this.hero = env.data;
      this.snotifyService.info("Hero saved.", '', notificationConfig());
      
    });
  }


  ngOnDestroy() {
    this.sub.unsubscribe();
  }


}
