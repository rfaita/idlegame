import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { ActionEffectTypes } from '../model/actionEffectTypes';

@Injectable()
export class ActionEffectTypesService {


    constructor(private http: HttpClient) {

    }

    get(): Observable<Envelope<ActionEffectTypes>> {
        return <Observable<Envelope<ActionEffectTypes>>>this.http.get(environment.API_BASE_URL + "actionEffectTypes");
    }

}