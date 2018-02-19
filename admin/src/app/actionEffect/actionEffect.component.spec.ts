import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionEffectComponent } from './actionEffect.component';

describe('ActionEffectComponent', () => {
  let component: ActionEffectComponent;
  let fixture: ComponentFixture<ActionEffectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActionEffectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActionEffectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
