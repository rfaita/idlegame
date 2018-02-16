import { Component, OnInit } from '@angular/core';
import { HeroTypeService } from '../service/herotype.service';
import { HeroType } from '../model/herotype';

@Component({
  selector: 'app-herotypes',
  templateUrl: './herotypes.component.html',
  styleUrls: ['./herotypes.component.css']
})
export class HeroTypesComponent implements OnInit {

  public heroesType: HeroType[];

  constructor(private heroTypeService: HeroTypeService) { }

  ngOnInit() {

    this.findAll();

  }

  public findAll() {
    this.heroTypeService.findAll().subscribe(env => this.heroesType = env.data);
  }

}
