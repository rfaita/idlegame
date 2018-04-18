import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { environment } from '../../environments/environment';
import { Envelope } from '../model/envelope';
import { Inventory } from '../model/shop/inventory';
import { InventoryItem } from '../model/shop/inventoryItem';
import { LootRoll } from '../model/shop/lootRoll';


@Injectable()
export class ShopService {

    constructor(private http: HttpClient) {

    }

    findAll(): Observable<Envelope<LootRoll[]>> {
        return <Observable<Envelope<LootRoll[]>>>this.http.get(environment.API_BASE_URL + "shop");
    }
}