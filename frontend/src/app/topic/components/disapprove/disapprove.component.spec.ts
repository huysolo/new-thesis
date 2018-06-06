import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisapproveComponent } from './disapprove.component';

describe('DisapproveComponent', () => {
  let component: DisapproveComponent;
  let fixture: ComponentFixture<DisapproveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisapproveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisapproveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
