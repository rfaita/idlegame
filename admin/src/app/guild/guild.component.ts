import { Component, OnInit } from '@angular/core';
import { HeroTypeService } from '../service/herotype.service';
import { HeroType } from '../model/herotype';
import { GuildService } from '../service/guild.service';
import { KeycloakService } from 'keycloak-angular';
import { Guild } from '../model/guild';
import { GuildMemberService } from '../service/guildmember.service';
import { GuildMember } from '../model/guildMember';
import { notificationConfig } from '../utils/helper';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'app-guild',
  templateUrl: './guild.component.html',
  styleUrls: ['./guild.component.css']
})
export class GuildComponent implements OnInit {

  public guild: Guild;
  public guildMember: GuildMember;
  public guildMembers: GuildMember[];

  public guildName: String;

  constructor(private guildService: GuildService,
    private guildMemberService: GuildMemberService,
    private snotifyService: SnotifyService,
    private keycloakService: KeycloakService) { }

  ngOnInit() {
    this.myGuild();

  }

  public sendGuildMemberRequest() {
    this.guildMemberService.sendGuildMemberRequest(this.guild.id).subscribe(env => {
      this.snotifyService.info("Request sended.", '', notificationConfig());
    });
  }

  public acceptGuildMemberRequest(memberRequestId: String) {
    this.guildMemberService.acceptGuildMemberRequest(memberRequestId).subscribe(env => {
      this.getGuildMembers();
      this.getGuildMembersRequest();
    });
  }
  public removeGuildMember(memberId: String) {
    this.guildMemberService.removeGuildMember(memberId).subscribe(env => {
      this.getGuildMembers();
      this.getGuildMembersRequest();
    });
  }
  public promoteGuildMember(memberId: String) {
    this.guildMemberService.promoteGuildMember(memberId).subscribe(env => {
      this.getGuildMembers();
      this.getGuildMembersRequest();
    });
  }
  public demoteGuildMember(memberId: String) {
    this.guildMemberService.demoteGuildMember(memberId).subscribe(env => {
      this.getGuildMembers();
      this.getGuildMembersRequest();
    });
  }

  public create() {
    let guild: Guild = new Guild();
    guild.name = this.guildName;
    this.guildService.create(guild).subscribe(env => {
      this.myGuild();
      this.guildName = "";
    });
  }

  public findByName() {
    this.guildService.findByName(this.guildName).subscribe(env => {
      this.guild = env.data;
      this.getGuildMembers();
    });
  }

  public myGuild() {
    this.guildService.myGuild().subscribe(env => {
      this.guild = env.data;
      if (this.guild != null) {
        this.getGuildMembers();
        this.guildMemberService.myGuildMember().subscribe(env => {
          this.guildMember = env.data;
          if (this.guildMember.type = "ADMIN") {
            this.getGuildMembersRequest();
          }

        });
      }
    });
  }

  public getGuildMembers() {
    this.guildMemberService.getGuildMembers(this.guild.id).subscribe(env => this.guildMembers = env.data)
  }

  public getGuildMembersRequest() {
    this.guildMemberService.getGuildMembersRequest(this.guild.id).subscribe(env => this.guildMembers.push(...env.data));
  }

}
