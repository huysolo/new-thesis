import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultTopicComponent } from './result-topic.component';

describe('ResultTopicComponent', () => {
  let component: ResultTopicComponent;
  let fixture: ComponentFixture<ResultTopicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResultTopicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultTopicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
