import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { HeroType } from '../model/herotype';
import { Envelope } from '../model/envelope';
import { Player } from '../model/player';

@Injectable()
export class PlayerService {


    constructor(private http: HttpClient) {
    }

    findByName(name: String): Observable<Envelope<Player>> {
        return <Observable<Envelope<Player>>>this.http.get(environment.API_BASE_URL + "player/findByName/" + name);
    }

    create(): Observable<Envelope<Player>> {
        return <Observable<Envelope<Player>>>this.http.post(environment.API_BASE_URL + "player", null);
    }

}