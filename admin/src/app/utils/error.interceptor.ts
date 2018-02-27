import { Injectable, Injector } from '@angular/core';
import {
    HttpEvent, HttpInterceptor, HttpHandler,
    HttpRequest, HttpErrorResponse, HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { _throw } from 'rxjs/observable/throw';
import 'rxjs/add/operator/catch';
import { SnotifyService } from 'ng-snotify';
import { notificationConfig } from './helper';

/**
 * Intercepts the HTTP responses, and in case that an error/exception is thrown, handles it
 * and extract the relevant information of it.
 */
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

    constructor(private snotifyService: SnotifyService) { }

    /**
     * Intercepts an outgoing HTTP request, executes it and handles any error that could be triggered in execution.
     * @see HttpInterceptor
     * @param req the outgoing HTTP request
     * @param next a HTTP request handler
     */
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req)
            .catch(errorResponse => {
                if (errorResponse instanceof HttpErrorResponse) {
                    if (errorResponse.status == 403) {
                        this.snotifyService.warning('You do not have authorization to that action.', '', notificationConfig());
                    } else if (errorResponse.status == 400) {
                        if (errorResponse.error.errors != null) {
                            for (let i in errorResponse.error.errors) {
                                this.snotifyService.warning(errorResponse.error.errors[i], '', notificationConfig());
                            }
                        }
                    } else {
                        this.snotifyService.error(errorResponse.message, '', notificationConfig());
                    }

                } else {
                    this.snotifyService.error(errorResponse.message ? errorResponse.message : errorResponse.toString(), '', notificationConfig());
                }
                return _throw(errorResponse);
            });
    }
}

/**
 * Provider POJO for the interceptor
 */
export const errorInterceptorProvider = {
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true,
};