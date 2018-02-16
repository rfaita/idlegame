import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { handleError } from '../utils/helper';
import { Envelope } from '../model/envelope';
import { Hero } from '../model/hero';

@Injectable()
export class HeroService {

    constructor(private http: HttpClient) {

    }

    findAllByPlayer(player: String): Observable<Envelope<Hero[]>> {
        return this.http.get(environment.API_BASE_URL + "hero/all/" + player)
            .catch(handleError);
    }

    findById(id: String): Observable<Envelope<Hero>> {
        return this.http.get(environment.API_BASE_URL + "hero/" + id)
            .catch(handleError);
    }

    save(hero: Hero): Observable<Envelope<Hero>> {
        return this.http.post(environment.API_BASE_URL + "hero", hero)
            .catch(handleError);
    }

    delete(id: String): Observable<Envelope<void>> {
        return this.http.delete(environment.API_BASE_URL + "hero/" + id)
            .catch(handleError);
    }
}



