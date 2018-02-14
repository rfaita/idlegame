import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { HeroType } from '../model/herotype';
import { extractData, handleError } from '../utils/helper';

@Injectable()
export class HeroTypeService {


    constructor(private http: Http) {

    }

    findAll(): Observable<HeroType[]> {
        return this.http.get(environment.API_BASE_URL + "heroType")
            .map(extractData)
            .catch(handleError);
    }

    findById(id: String): Observable<HeroType> {
        return this.http.get(environment.API_BASE_URL + "heroType/" + id)
            .map(extractData)
            .catch(handleError);
    }

    save(heroType: HeroType): Observable<HeroType> {
        return this.http.post(environment.API_BASE_URL + "heroType", heroType)
            .map(extractData)
            .catch(handleError);
    }

}