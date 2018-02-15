import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { ActionEffectTypes } from '../model/actionEffectTypes';
import { ActionEffect } from '../model/actionEffect';
import { clone } from '../utils/helper';
import { BuffEffect } from '../model/buffEffect';

@Component({
  selector: 'app-actioneffect',
  templateUrl: './actionEffect.component.html',
  styleUrls: ['./actionEffect.component.css']
})
export class ActionEffectComponent implements OnInit, OnDestroy {

  @Input() actionEffectTypes: ActionEffectTypes;

  @Input() actionEffect: ActionEffect;

  @Input() actionEffectName: String;

  @Output() result: EventEmitter<ActionEffect> = new EventEmitter<ActionEffect>();

  constructor() { }

  ngOnInit() {

  }
  public refresh() {
    this.result.emit(this.actionEffect);
  }

  public changeActionEffect() {

    if (this.actionEffect == null) {
      this.actionEffect = new ActionEffect();
    } else {
      this.actionEffect = null;
    }
    this.refresh();
  }

  public remove() {
    this.actionEffect = null;
    this.refresh();
  }

  public addBuffEffectActionEffect() {
    if (this.actionEffect.buffEffects == null) {
      this.actionEffect.buffEffects = [];
    }
    this.actionEffect.buffEffects.push(new BuffEffect());
    this.refresh();
  }

  public changeBuffEffectActionEffect(event: BuffEffect, i: number) {
    if (event != null) {
      this.actionEffect.buffEffects[i] = event;
    } else {
      this.actionEffect.buffEffects.splice(i, 1);
    }
    this.refresh();
  }

  ngOnDestroy() {

  }


}
