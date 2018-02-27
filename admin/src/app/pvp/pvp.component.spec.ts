import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PvpComponent } from './pvp.component';

describe('TableListComponent', () => {
  let component: PvpComponent;
  let fixture: ComponentFixture<PvpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PvpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PvpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
