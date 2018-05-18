import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeetingDetailPageComponent } from './meeting-detail-page.component';

describe('MeetingDetailPageComponent', () => {
  let component: MeetingDetailPageComponent;
  let fixture: ComponentFixture<MeetingDetailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeetingDetailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeetingDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
