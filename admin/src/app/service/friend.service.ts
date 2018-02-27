import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { Friend } from '../model/friend';

@Injectable()
export class FriendService {

    constructor(private http: HttpClient) {
    }

    friends(): Observable<Envelope<Friend[]>> {
        return <Observable<Envelope<Friend[]>>>this.http.get(environment.API_BASE_URL + "friend");
    }

    sendFriendRequest(userFriend: String): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.post(environment.API_BASE_URL + "friend/" + userFriend, null);
    }

    removeFriend(friendId: String): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.delete(environment.API_BASE_URL + "friend/" + friendId);
    }


    acceptFriendRequest(friendId: String): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.put(environment.API_BASE_URL + "friend/" + friendId, null);
    }

}



