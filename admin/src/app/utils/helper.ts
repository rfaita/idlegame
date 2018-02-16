import { HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

declare var $: any;

export function showNotification(type, msg) {

    $.notify({
        icon: "notifications",
        message: msg

    }, {
            type: type,
            timer: 4000,
            placement: {
                from: 'top',
                align: 'center'
            }
        });
}

export function handleError(error: HttpErrorResponse | any) {
    // In a real world app, you might use a remote logging infrastructure

    if (error instanceof HttpErrorResponse) {
        if (error.status == 403) {
            showNotification('danger', 'You do not have authorization to that action.');
        } else if (error.status == 400) {            
            showNotification('warning', 'ARRUMAR ISSO');
        } else {
            showNotification('danger', error.message);
        }

    } else {
        showNotification('danger', error.message ? error.message : error.toString());
    }
    return Observable.throw(error);
}

export function clone(obj: any) {
    if (obj == null) {
        return null;
    }
    return JSON.parse(JSON.stringify(obj));
}