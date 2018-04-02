import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Message } from '../model/message';
import { KeycloakService } from 'keycloak-angular';
import { ChatService } from '../service/chat.service';


@Component({
  selector: 'app-chatpm',
  templateUrl: './chatpm.component.html',
  styleUrls: ['./chatpm.component.css']
})
export class ChatPmComponent implements OnInit, OnDestroy {

  @Input()
  public messages: Message[];
  @Input()
  public index: number;

  public text: String;

  public nickName: String;
  public minimized = false;


  constructor(private keycloakService: KeycloakService,
    private chatService: ChatService) { }

  public calcPosition() {
    let styles = {
      'right': (this.index * 240) + "px"
    };
    return styles;

  }

  public changeMinimized() {
    this.minimized = !this.minimized;
  }


  public sendPrivateMessage() {
    if (this.text != null && this.text != "") {

      let firstMessage: Message = this.messages[0];

      let message: Message = new Message();
      message.text = this.text;

      let dest: String = this.nickName == firstMessage.fromUserNickName ? firstMessage.toUserId : firstMessage.fromUserId;

      this.chatService.sendPrivateMessage(dest, message);

      this.text = "";
    }
  }

  public close() {

  }

  ngOnInit() {
    this.nickName = this.keycloakService.getUsername();
  }


  ngOnDestroy() {

  }


}
