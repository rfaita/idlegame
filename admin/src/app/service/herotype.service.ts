import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { HeroType } from '../model/herotype';
import { handleError } from '../utils/helper';
import { Envelope } from '../model/envelope';

@Injectable()
export class HeroTypeService {


    constructor(private http: HttpClient) {

    }

    findAll(): Observable<Envelope<HeroType[]>> {
        return this.http.get(environment.API_BASE_URL + "heroType")
            .catch(handleError);
    }

    findById(id: String): Observable<Envelope<HeroType>> {
        return this.http.get(environment.API_BASE_URL + "heroType/" + id)
            .catch(handleError);
    }

    save(heroType: HeroType): Observable<Envelope<HeroType>> {
        return this.http.post(environment.API_BASE_URL + "heroType", heroType)
            .catch(handleError);
    }

}