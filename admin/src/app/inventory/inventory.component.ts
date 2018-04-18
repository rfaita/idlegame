import { Component, OnInit } from '@angular/core';
import { Inventory } from '../model/shop/inventory';
import { InventoryService } from '../service/inventory.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {

  public inventory: Inventory;

  constructor(private inventoryService: InventoryService) { }

  ngOnInit() {

    this.findByUserId();

  }

  public findByUserId() {
    this.inventoryService.findByUserId().subscribe(env => this.inventory = env.data);
  }

}
