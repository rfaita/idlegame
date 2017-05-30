import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Hero } from '../model/hero';
import { HeroesService } from '../service/heroes.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit {

  public hero: Hero;

  constructor(private route: ActivatedRoute,
    private location: Location,
    private heroesService: HeroesService) { }

  public back() {
    this.location.back();
  }

  public levelUp() {
    this.heroesService.levelUp(this.hero.id)
      .subscribe(
      hero => this.hero = hero,
      error => console.log(error)
      );
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let id: number = +params['id']; // (+) converts string 'id' to a number
      this.heroesService.getHero(id)
        .subscribe(
        hero => this.hero = hero,
        error => console.log(error)
        );
    });
  }

}
