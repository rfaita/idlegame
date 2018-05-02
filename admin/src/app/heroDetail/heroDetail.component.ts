import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';
import { ActivatedRoute } from "@angular/router";
import { BattleHero } from '../model/battleHero';
import { BattleHeroService } from '../service/battleHero.service';

@Component({
  selector: 'app-heroDetail',
  templateUrl: './heroDetail.component.html',
  styleUrls: ['./heroDetail.component.css']
})
export class HeroDetailComponent implements OnInit, OnDestroy {

  public battleHero: BattleHero;


  private sub: any;

  constructor(private route: ActivatedRoute,
    private battleHeroService: BattleHeroService) { }

  ngOnInit() {

    this.findById();

  }

  public findById() {

    this.sub = this.route.params.subscribe(params => {
      let id: string = params['id'];
      this.battleHeroService.findById(id).subscribe(env => {
        this.battleHero = env.data;

      });
    });

  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }


}
