import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageStandardComponent } from './manage-standard.component';

describe('ManageStandardComponent', () => {
  let component: ManageStandardComponent;
  let fixture: ComponentFixture<ManageStandardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageStandardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageStandardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
