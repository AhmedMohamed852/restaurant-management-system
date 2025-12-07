import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowMyContactComponent } from './show-my-contact.component';

describe('ShowMyContactComponent', () => {
  let component: ShowMyContactComponent;
  let fixture: ComponentFixture<ShowMyContactComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowMyContactComponent]
    });
    fixture = TestBed.createComponent(ShowMyContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
