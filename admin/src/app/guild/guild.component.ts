import { Component, OnInit } from '@angular/core';
import { HeroTypeService } from '../service/herotype.service';
import { HeroType } from '../model/herotype';
import { GuildService } from '../service/guild.service';
import { KeycloakService } from 'keycloak-angular';
import { Guild } from '../model/guild';
import { GuildMemberService } from '../service/guildmember.service';
import { GuildMember } from '../model/guildMember';

@Component({
  selector: 'app-guild',
  templateUrl: './guild.component.html',
  styleUrls: ['./guild.component.css']
})
export class GuildComponent implements OnInit {

  private subject: String;
  public guild: Guild;
  public guildMembers: GuildMember[];

  public guildName: String;

  constructor(private guildService: GuildService,
    private guildMemberService: GuildMemberService,
    private keycloakService: KeycloakService) { }

  ngOnInit() {

    this.subject = this.keycloakService.getKeycloakInstance().subject;

    this.findByUserOwner();

  }

  public create() {
    let guild: Guild = new Guild();
    guild.name = this.guildName;
    this.guildService.create(guild).subscribe(env => {
      this.findByUserOwner();
      this.guildName = "";
    });

    
  }

  public findByUserOwner() {
    this.guildService.findByUserOwner(this.subject).subscribe(env => {
      this.guild = env.data;
      this.getGuildMembers();
    });
  }

  public getGuildMembers() {
    this.guildMemberService.getGuildMembers(this.guild.id).subscribe(env => this.guildMembers = env.data)
  }

}
