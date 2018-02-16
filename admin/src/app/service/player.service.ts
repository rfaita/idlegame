import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { HeroType } from '../model/herotype';
import { handleError } from '../utils/helper';
import { Envelope } from '../model/envelope';
import { Player } from '../model/player';

@Injectable()
export class PlayerService {


    constructor(private http: HttpClient) {

    }

    findByName(name:String): Observable<Envelope<Player>> {
        return this.http.get(environment.API_BASE_URL + "player/findByName/" + name)
            .catch(handleError);
    }

}