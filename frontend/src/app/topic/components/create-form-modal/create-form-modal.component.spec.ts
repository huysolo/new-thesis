import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFormModalComponent } from './create-form-modal.component';

describe('CreateFormModalComponent', () => {
  let component: CreateFormModalComponent;
  let fixture: ComponentFixture<CreateFormModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateFormModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFormModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
