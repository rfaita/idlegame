import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

export function extractData(res: Response): any {
    let body = res.json();
    return body.data || {};
}

export function handleError(error: Response | any) {
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