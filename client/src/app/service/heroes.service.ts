import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { Hero } from '../model/hero';
import { environment } from '../../environments/environment';

@Injectable()
export class HeroesService {


    constructor(private http: Http) {

    }

    generateHero(): Observable<Hero> {
        return this.http.get(environment.API_BASE_URL + "hero/generate")
            .map(this.extractData)
            .catch(this.handleError);
    }


    getHero(id: number): Observable<Hero> {
        return this.http.get(environment.API_BASE_URL + "hero/" + id)
            .map(this.extractData)
            .catch(this.handleError);
    }

    levelUp(id: string): Observable<Hero> {
        return this.http.get(environment.API_BASE_URL + "hero/levelUp/" + id, null)
            .map(this.extractData)
            .catch(this.handleError);
    }

    getHeroes(): Observable<Hero[]> {
        return this.http.get(environment.API_BASE_URL + "hero/all/1")
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
