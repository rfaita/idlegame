import { Component, OnInit } from '@angular/core';
import { Inventory } from '../model/shop/inventory';
import { InventoryService } from '../service/inventory.service';
import { ShopService } from '../service/shop.service';
import { LootRoll } from '../model/shop/lootRoll';
import { SnotifyService } from 'ng-snotify';
import { notificationConfig } from '../utils/helper';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  public lootRolls: LootRoll[];

  constructor(private shopService: ShopService,
    private inventoryService: InventoryService,
    private snotifyService: SnotifyService) { }

  ngOnInit() {

    this.findAll();

  }

  public findAll() {
    this.shopService.findAll().subscribe(env => this.lootRolls = env.data);
  }

  public buyItem(itemId: String) {
    this.inventoryService.buyItem(itemId).subscribe(env =>
      this.snotifyService.info("Buyed Item.", '', notificationConfig())
    );
  }

}
