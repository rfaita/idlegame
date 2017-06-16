import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DungeonComponent } from './dungeon.component';

describe('DungeonComponent', () => {
  let component: DungeonComponent;
  let fixture: ComponentFixture<DungeonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DungeonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DungeonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
