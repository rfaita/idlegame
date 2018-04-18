import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { Inventory } from '../model/shop/inventory';
import { InventoryItem } from '../model/shop/inventoryItem';


@Injectable()
export class InventoryService {

    constructor(private http: HttpClient) {

    }

    findByUserId(): Observable<Envelope<Inventory>> {
        return <Observable<Envelope<Inventory>>>this.http.get(environment.API_BASE_URL + "inventory");
    }

    rollItem(item: InventoryItem): Observable<Envelope<Inventory>> {
        return <Observable<Envelope<Inventory>>>this.http.post(environment.API_BASE_URL + "inventory/rollItem", item);
    }

    buyItem(itemId: String): Observable<Envelope<Inventory>> {
        return <Observable<Envelope<Inventory>>>this.http.put(environment.API_BASE_URL + "inventory/" + itemId, null);
    }

}