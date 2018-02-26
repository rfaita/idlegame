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

export function showMailNotification(msg) {

    $.notify({
        icon: "notifications",
        message: msg

    }, {
            type: "info",
            timer: 2000,
            placement: {
                from: 'top',
                align: 'right'
            }
        });
}

export function handleError(httpError: HttpErrorResponse | any) {
    // In a real world app, you might use a remote logging infrastructure

    if (httpError instanceof HttpErrorResponse) {
        if (httpError.status == 403) {
            showNotification('danger', 'You do not have authorization to that action.');
        } else if (httpError.status == 400) {
            if (httpError.error.errors != null) {
                for (let i in httpError.error.errors) {
                    showNotification('warning', httpError.error.errors[i]);
                }
            }
        } else {
            showNotification('danger', httpError.message);
        }

    } else {
        showNotification('danger', httpError.message ? httpError.message : httpError.toString());
    }
    return Observable.throw(httpError);
}

export function clone(obj: any) {
    if (obj == null) {
        return null;
    }
    return JSON.parse(JSON.stringify(obj));
}