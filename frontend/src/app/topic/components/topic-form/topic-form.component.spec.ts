/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { TopicFormComponent } from './topic-form.component';

describe('TopicFormComponent', () => {
  let component: TopicFormComponent;
  let fixture: ComponentFixture<TopicFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopicFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopicFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
