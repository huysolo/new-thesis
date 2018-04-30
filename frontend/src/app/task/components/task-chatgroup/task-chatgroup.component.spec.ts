import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskChatgroupComponent } from './task-chatgroup.component';

describe('TaskChatgroupComponent', () => {
  let component: TaskChatgroupComponent;
  let fixture: ComponentFixture<TaskChatgroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskChatgroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskChatgroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
