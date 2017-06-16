import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuildformationComponent } from './buildformation.component';

describe('BuildformationComponent', () => {
  let component: BuildformationComponent;
  let fixture: ComponentFixture<BuildformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuildformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuildformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
