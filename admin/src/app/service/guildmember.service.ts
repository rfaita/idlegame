import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { GuildMember } from '../model/guildMember';


@Injectable()
export class GuildMemberService {

    constructor(private http: HttpClient) {
    }

    getGuildMembers(guildId: String): Observable<Envelope<GuildMember[]>> {
        return <Observable<Envelope<GuildMember[]>>>this.http.get(environment.API_BASE_URL + "guildMember/" + guildId);
    }

    sendGuildMemberRequest(guildId: String): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.post(environment.API_BASE_URL + "guildMember/" + guildId, null);
    }


    acceptGuildMemberRequest(memberRequestId: String): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.put(environment.API_BASE_URL + "guildMember/" + memberRequestId, null);
    }


    removeGuildMember(memberId: String): Observable<Envelope<void>> {
        return <Observable<Envelope<void>>>this.http.delete(environment.API_BASE_URL + "guildMember/" + memberId);
    }


}