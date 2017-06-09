import { Component, OnInit } from '@angular/core';
import { Player } from './model/player';
import { PlayerService } from './service/player.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public showingDialog: boolean = false;
  public player: Player;

  constructor(private playerService: PlayerService) { }

  public showDialog() {
    this.showingDialog = true;
  }

  public hideDialog() {
    this.showingDialog = false;
  }

  ngOnInit() {
    this.playerService.getPlayer()
      .subscribe(
      player => this.player = player,
      error => console.log(error));
  }

}
