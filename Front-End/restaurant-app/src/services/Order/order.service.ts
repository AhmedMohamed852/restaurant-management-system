import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CartItemService} from "../cartItem/cart-item.service";
import {map, Observable} from "rxjs";
import {OrderItem} from "../../models/order-item";
import {OrderRequest} from "../../models/order-request";
import {UserIdService} from "../user-id.service";
import {Product} from "../../models/product";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient ,private cartService: CartItemService , private userIdService:UserIdService) { }

  baseUrl = "http://localhost:8081/api/orders/";

  // @ts-ignore
  submitOrder():Observable<any>
  {
    const cartItems = this.cartService.getCartItems();

    const orderItems: OrderItem[] = cartItems.map((item: { id: any; quantity: any; }) => ({
      productId : item.id ,
      quantity : item.quantity
    }));

    const orderRequest: OrderRequest =
    {
      orderItems: orderItems
    }

    return this.http.post(this.baseUrl +"submit", orderRequest);
  }


  viewHistory():Observable<any>
  {
    const userId = this.userIdService.getCurrentUserId();
    return this.http.get(this.baseUrl +"getAllOrdersByUserId/" + userId).pipe(map(response => response))
  }


  viewAllHistory():Observable<any>
  {
    return this.http.get<any>(this.baseUrl + "getAllOrders" )
      .pipe(map(response => response));
  }

}
