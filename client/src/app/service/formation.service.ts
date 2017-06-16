import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Formation } from '../model/formation';
import { PvpRoll } from '../model/pvpRoll';

@Injectable()
export class FormationService {


    constructor(private http: Http) {

    }

    getNextLevelFormationPve(): Observable<Formation> {
        return this.http.get(environment.API_BASE_URL + "formation/nextLevelFormationPve")
            .map(this.extractData)
            .catch(this.handleError);
    }

    getNextLevelFormationDungeon(): Observable<Formation> {
        return this.http.get(environment.API_BASE_URL + "formation/nextLevelFormationDungeon")
            .map(this.extractData)
            .catch(this.handleError);
    }

    getFormationByAllocation(allocation: string): Observable<Formation> {
        return this.http.get(environment.API_BASE_URL + "formation/allocation/" + allocation)
            .map(this.extractData)
            .catch(this.handleError);
    }

    putFormation(formation: Formation): Observable<Formation> {
        return this.http.put(environment.API_BASE_URL + "formation", formation)
            .map(this.extractData)
            .catch(this.handleError);
    }

    pvpRoll(): Observable<PvpRoll> {
        return this.http.get(environment.API_BASE_URL + "formation/pvpRoll")
            .map(this.extractData)
            .catch(this.handleError);
    }

    pvpRollPaid(): Observable<PvpRoll> {
        return this.http.get(environment.API_BASE_URL + "formation/pvpRoll/paid")
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
