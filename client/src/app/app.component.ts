import { Component, OnInit } from '@angular/core';
import { Player } from './model/player';
import { PlayerService } from './service/player.service';
import { GameService } from './service/game.service';
import { SocketMessage } from './model/socketMessage';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public showingDialog: boolean = false;
  public player: Player;

  constructor(private playerService: PlayerService, private gameService: GameService) { }

  public showDialog() {
    this.showingDialog = true;
  }

  public hideDialog() {
    this.showingDialog = false;
  }

  ngOnInit() {


    this.gameService.subject
      .filter(sm => sm.action == "computeResources")
      .subscribe(sm => {
        this.player = sm.object;
      });

    this.computeResources();
    setInterval(() => {
      this.computeResources();
    }, 5000);
  }

  private computeResources() {
    let sm: SocketMessage = new SocketMessage();
    sm.action = "computeResources"
    this.gameService.send(sm);
  }

}
