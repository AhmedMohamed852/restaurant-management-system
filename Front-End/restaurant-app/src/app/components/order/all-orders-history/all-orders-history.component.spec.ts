import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllOrdersHistoryComponent } from './all-orders-history.component';

describe('AllOrdersHistoryComponent', () => {
  let component: AllOrdersHistoryComponent;
  let fixture: ComponentFixture<AllOrdersHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllOrdersHistoryComponent]
    });
    fixture = TestBed.createComponent(AllOrdersHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
