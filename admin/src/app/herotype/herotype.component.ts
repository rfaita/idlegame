import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Location } from '@angular/common';
import { HeroTypeService } from '../service/herotype.service';
import { HeroType } from '../model/herotype';
import { ActivatedRouteSnapshot } from '@angular/router';


@Component({
  selector: 'app-herotype',
  templateUrl: './herotype.component.html',
  styleUrls: ['./herotype.component.css']
})
export class HeroTypeComponent implements OnInit, OnDestroy {

  public heroType: HeroType;

  private sub: any;

  constructor(private route: ActivatedRoute,
    private location: Location,
    private heroTypeService: HeroTypeService) { }

  ngOnInit() {

    this.findById();

  }

  public findById() {

    this.sub = this.route.params.subscribe(params => {
      let id: string = params['id'];
      this.heroTypeService.findById(id)
        .subscribe(
        heroType => this.heroType = heroType,
        error => console.log(error)
        );
    });

  }

  public save(data:any) {
    data.id = this.heroType.id;
    
    this.heroTypeService.save(data)
      .subscribe(
      heroType => this.heroType = heroType,
      error => console.log(error)
      );;
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }


}
