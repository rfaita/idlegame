import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { ActionEffectTypes } from '../model/actionEffectTypes';
import { clone } from '../utils/helper';
import { BuffEffect } from '../model/buffEffect';

@Component({
  selector: 'app-buffeffect',
  templateUrl: './buffEffect.component.html',
  styleUrls: ['./buffEffect.component.css']
})
export class BuffEffectComponent implements OnInit, OnDestroy {

  @Input() actionEffectTypes: ActionEffectTypes;

  @Input() buffEffect: BuffEffect;

  @Input() buffEffectName: String;

  @Output() result: EventEmitter<BuffEffect> = new EventEmitter<BuffEffect>();

  constructor() { }

  ngOnInit() {

  }
  public refresh() {
    this.result.emit(this.buffEffect);
  }

  public changeBuffEffect() {
    if (this.buffEffect == null) {
      this.buffEffect = new BuffEffect();
    } else {
      this.buffEffect = null;
    }
    this.refresh();
  }

  public remove() {
    this.buffEffect = null;
    this.refresh();
  }

  ngOnDestroy() {

  }


}
