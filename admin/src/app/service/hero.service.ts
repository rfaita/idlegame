import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { Hero } from '../model/hero';

@Injectable()
export class HeroService {

    constructor(private http: HttpClient) {

    }

    findAllByUser(userId: String): Observable<Envelope<Hero[]>> {
        return <Observable<Envelope<Hero[]>>>this.http.get(environment.API_BASE_URL + "hero/all/" + userId);
    }

    findById(id: String): Observable<Envelope<Hero>> {
        return <Observable<Envelope<Hero>>>this.http.get(environment.API_BASE_URL + "hero/" + id);
    }

    customRoll(userId: String, heroType: String, heroQuality: String): Observable<Envelope<Hero>> {
        return <Observable<Envelope<Hero>>>this.http.get(environment.API_BASE_URL + "hero/customRoll/" + userId + "/" + heroType + "/" + heroQuality);
    }

    save(hero: Hero): Observable<Envelope<Hero>> {
        return <Observable<Envelope<Hero>>>this.http.post(environment.API_BASE_URL + "hero", hero);
    }

    delete(id: String): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.delete(environment.API_BASE_URL + "hero/" + id);
    }
}



