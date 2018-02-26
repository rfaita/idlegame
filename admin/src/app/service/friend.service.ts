import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { handleError } from '../utils/helper';
import { Envelope } from '../model/envelope';
import { Friend } from '../model/friend';

@Injectable()
export class FriendService {

    constructor(private http: HttpClient) {

    }

    friends(): Observable<Envelope<Friend[]>> {
        return this.http.get(environment.API_BASE_URL + "friend")
            .catch(handleError);
    }

    sendFriendRequest(userFriend: String): Observable<Envelope<void>> {
        return this.http.post(environment.API_BASE_URL + "friend/" + userFriend, null)
            .catch(handleError);
    }

    removeFriend(friendId: String): Observable<Envelope<void>> {
        return this.http.delete(environment.API_BASE_URL + "friend/" + friendId)
            .catch(handleError);
    }


    acceptFriendRequest(friendId: String): Observable<Envelope<void>> {
        return this.http.put(environment.API_BASE_URL + "friend/" + friendId, null)
            .catch(handleError);
    }

}



