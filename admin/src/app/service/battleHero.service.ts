import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { BattleHero } from '../model/battleHero';


@Injectable()
export class BattleHeroService {

    constructor(private http: HttpClient) {

    }

    findById(id: String): Observable<Envelope<BattleHero>> {
        return <Observable<Envelope<BattleHero>>>this.http.get(environment.API_BASE_URL + "battleHero/" + id);
    }

}



