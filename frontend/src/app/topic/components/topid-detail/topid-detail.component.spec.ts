import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopidDetailComponent } from './topid-detail.component';

describe('TopidDetailComponent', () => {
  let component: TopidDetailComponent;
  let fixture: ComponentFixture<TopidDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopidDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopidDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
