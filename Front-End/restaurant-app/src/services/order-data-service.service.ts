// 📁 src/app/services/order-data.service.ts

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderDataService {
  private orderResponse: any = null;

  constructor() { }

  setOrderData(data: any): void
  {
    this.orderResponse = data;
  }

  getOrderData(): any
  {
    return this.orderResponse;
  }

  clearOrderData(): void {
    this.orderResponse = null;
  }

  private allOrderResponse: any = null;


  setAllOrderData(data: any): void
  {
    this.orderResponse = data;
  }

  getAllOrderData(): any
  {
    return this.orderResponse;
  }

  clearAllOrderData(): void {
    this.orderResponse = null;
  }
}
