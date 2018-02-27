import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { HeroType } from '../model/herotype';
import { Envelope } from '../model/envelope';

@Injectable()
export class HeroTypeService {


    constructor(private http: HttpClient) {

    }

    findAll(): Observable<Envelope<HeroType[]>> {
        return <Observable<Envelope<HeroType[]>>>this.http.get(environment.API_BASE_URL + "heroType");
    }

    findById(id: String): Observable<Envelope<HeroType>> {
        return <Observable<Envelope<HeroType>>>this.http.get(environment.API_BASE_URL + "heroType/" + id);
    }

    save(heroType: HeroType): Observable<Envelope<HeroType>> {
        return <Observable<Envelope<HeroType>>>this.http.post(environment.API_BASE_URL + "heroType", heroType);
    }

}