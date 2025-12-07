import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllContactsComponent } from './show-all-contacts.component';

describe('ShowAllContactsComponent', () => {
  let component: ShowAllContactsComponent;
  let fixture: ComponentFixture<ShowAllContactsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowAllContactsComponent]
    });
    fixture = TestBed.createComponent(ShowAllContactsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
