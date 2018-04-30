import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskPaginationComponent } from './task-pagination.component';

describe('TaskPaginationComponent', () => {
  let component: TaskPaginationComponent;
  let fixture: ComponentFixture<TaskPaginationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaskPaginationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaskPaginationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
