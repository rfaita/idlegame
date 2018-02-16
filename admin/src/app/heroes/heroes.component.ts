import { Component, OnInit } from '@angular/core';
import { Hero } from '../model/hero';
import { HeroService } from '../service/hero.service';
import { PlayerService } from '../service/player.service';
import { showNotification } from '../utils/helper';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  public heroes: Hero[];
  public player: String;

  constructor(private heroService: HeroService,
    private playerService: PlayerService) { }

  ngOnInit() {


  }

  public keyDownFindAllByPlayer(event: any) {
    if (event.keyCode == 13) {
      this.findAllByPlayer();
    }
  }

  public findAllByPlayer() {
    this.playerService.findByName(this.player).subscribe(env => {
      this.heroService.findAllByPlayer(env.data.id).subscribe(env => this.heroes = env.data);
    });
  }

  public delete(id: String) {
    this.heroService.delete(id).subscribe(env => {
      this.findAllByPlayer();
      showNotification("info", "Hero deleted.");
    });
  }

}
