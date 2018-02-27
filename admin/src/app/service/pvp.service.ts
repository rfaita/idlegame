import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { PvpRating } from '../model/pvpRating';
import { Battle } from '../model/battle';

@Injectable()
export class PvpService {

    constructor(private http: HttpClient) {

    }

    get(): Observable<Envelope<PvpRating[]>> {
        return <Observable<Envelope<PvpRating[]>>>this.http.get(environment.API_BASE_URL + "pvp");
    }

    roll(): Observable<Envelope<PvpRating[]>> {
        return <Observable<Envelope<PvpRating[]>>>this.http.get(environment.API_BASE_URL + "pvp/roll");
    }

    clearRoll(): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.delete(environment.API_BASE_URL + "pvp");
    }

    battle(idPvpRating: String): Observable<Envelope<Battle>> {
        return <Observable<Envelope<Battle>>>this.http.get(environment.API_BASE_URL + "pvp/battle/" + idPvpRating);

    }


}



