import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopicFormButtonComponent } from './topic-form-button.component';

describe('TopicFormButtonComponent', () => {
  let component: TopicFormButtonComponent;
  let fixture: ComponentFixture<TopicFormButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopicFormButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopicFormButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
