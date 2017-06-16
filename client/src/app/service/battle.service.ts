import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Formation } from '../model/formation';
import { BattleRetorno } from '../model/battleRetorno';

@Injectable()
export class BattleService {


    constructor(private http: Http) {

    }

    doBattlePve(): Observable<BattleRetorno> {
        return this.http.get(environment.API_BASE_URL + "battle/pve")
            .map(this.extractData)
            .catch(this.handleError);
    }

    doBattleDungeon(): Observable<BattleRetorno> {
        return this.http.get(environment.API_BASE_URL + "battle/dungeon")
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.data || {};
    }

    private handleError(error: Response | any) {
        // In a real world app, you might use a remote logging infrastructure
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        return Observable.throw(errMsg);
    }
}
