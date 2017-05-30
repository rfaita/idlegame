import { Component, OnInit } from '@angular/core';
import { ShopService } from './shop.service';
import { Hero } from '../model/hero';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  public hero: Hero;

  constructor(private router: Router, private shopService: ShopService) { }

  ngOnInit() {
  }

  public generateHero() {
    this.shopService.generateHero()
      .subscribe(
      hero => {
        this.hero = hero;
        this.router.navigate(['/hero', hero.id]);
      },
      error => console.log(error));
  }

}
