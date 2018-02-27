import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { Formation } from '../model/formation';

@Injectable()
export class FormationService {

    constructor(private http: HttpClient) {
    }

    save(formation: Formation): Observable<Envelope<Formation>> {
        return <Observable<Envelope<Formation>>>this.http.post(environment.API_BASE_URL + "formation", formation);
    }


}



