import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { HeroType } from '../model/herotype';
import { Envelope } from '../model/envelope';
import { User } from '../model/user';

@Injectable()
export class UserService {


    constructor(private http: HttpClient) {
    }

    findByNickName(name: String): Observable<Envelope<User>> {
        return <Observable<Envelope<User>>>this.http.get(environment.API_BASE_URL + "user/findByNickName/" + name);
    }

    create(): Observable<Envelope<User>> {
        return <Observable<Envelope<User>>>this.http.post(environment.API_BASE_URL + "user", null);
    }

}