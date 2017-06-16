import { Component, OnInit, Input } from '@angular/core';
import { Hero } from '../model/hero';

@Component({
  selector: 'formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent implements OnInit {

  @Input()
  public title: string;
  @Input()
  public data: Hero[];

  constructor() { }

  ngOnInit() {
  }

}
