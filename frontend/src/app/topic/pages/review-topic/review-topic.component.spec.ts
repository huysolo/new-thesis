import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewTopicComponent } from './review-topic.component';

describe('ReviewTopicComponent', () => {
  let component: ReviewTopicComponent;
  let fixture: ComponentFixture<ReviewTopicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewTopicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewTopicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
