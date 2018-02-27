import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { HeroTypeTypes } from '../model/herotypetypes';
import { HeroTypes } from '../model/herotypes';

@Injectable()
export class HeroTypesService {


    constructor(private http: HttpClient) {

    }

    get(): Observable<Envelope<HeroTypes>> {
        return <Observable<Envelope<HeroTypes>>>this.http.get(environment.API_BASE_URL + "heroTypes");
    }

}