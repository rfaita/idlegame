import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { handleError } from '../utils/helper';
import { Envelope } from '../model/envelope';

@Injectable()
export class BattleService {

    constructor(private http: HttpClient) {

    }

    doBattle(attFormation: String, defFormation: String): Observable<Envelope<any>> {
        return this.http.get(environment.API_BASE_URL + "battle/" + attFormation + "/" + defFormation)
            .catch(handleError);
    }


}



