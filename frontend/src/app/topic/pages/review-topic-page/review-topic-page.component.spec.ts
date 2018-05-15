import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewTopicPageComponent } from './review-topic-page.component';

describe('ReviewTopicPageComponent', () => {
  let component: ReviewTopicPageComponent;
  let fixture: ComponentFixture<ReviewTopicPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewTopicPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewTopicPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
