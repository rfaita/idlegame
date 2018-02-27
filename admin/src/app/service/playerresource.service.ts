import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { Battle } from '../model/battle';
import { PlayerResource } from '../model/playerResource';

@Injectable()
export class PlayerResourceService {

    constructor(private http: HttpClient) {

    }

    computeResources(): Observable<Envelope<PlayerResource>> {
        return <Observable<Envelope<PlayerResource>>>this.http.get(environment.API_BASE_URL + "playerResource/computeResources");
    }


}



