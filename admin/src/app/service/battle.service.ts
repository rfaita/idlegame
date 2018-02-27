import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { Battle } from '../model/battle';

@Injectable()
export class BattleService {

    constructor(private http: HttpClient) {

    }

    doBattle(attFormation: String, defFormation: String): Observable<Envelope<Battle>> {
        return <Observable<Envelope<Battle>>>this.http.get(environment.API_BASE_URL + "battle/" + attFormation + "/" + defFormation);
    }


}



