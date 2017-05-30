import { Component, OnInit } from '@angular/core';
import { Hero } from '../model/hero';
import { HeroesService } from '../service/heroes.service';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  public heroes: Hero[];

  constructor(private heroesService: HeroesService) { }

  public getHeroes() {
    this.heroesService.getHeroes()
      .subscribe(
      heroes => this.heroes = heroes,
      error => console.log(error));
  }

  ngOnInit() {
    this.getHeroes();
  }

}
