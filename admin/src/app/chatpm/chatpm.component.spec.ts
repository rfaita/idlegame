import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatPmComponent } from './chatpm.component';

describe('UserProfileComponent', () => {
  let component: ChatPmComponent;
  let fixture: ComponentFixture<ChatPmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChatPmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatPmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
