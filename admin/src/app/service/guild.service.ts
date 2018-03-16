import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { HeroType } from '../model/herotype';
import { Envelope } from '../model/envelope';
import { Player } from '../model/player';
import { Guild } from '../model/guild';

@Injectable()
export class GuildService {

    constructor(private http: HttpClient) {
    }

    create(guild: Guild): Observable<Envelope<Guild>> {
        return <Observable<Envelope<Guild>>>this.http.post(environment.API_BASE_URL + "guild", guild);
    }

    findByUserOwner(user: String): Observable<Envelope<Guild>> {
        return <Observable<Envelope<Guild>>>this.http.get(environment.API_BASE_URL + "guild/findByUserOwnerId/" + user);
    }

    findByName(name: String): Observable<Envelope<Guild>> {
        return <Observable<Envelope<Guild>>>this.http.get(environment.API_BASE_URL + "guild/findByName/" + name);
    }

    myGuild(): Observable<Envelope<Guild>> {
        return <Observable<Envelope<Guild>>>this.http.get(environment.API_BASE_URL + "guild");
    }

}