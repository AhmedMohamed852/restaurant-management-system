import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CartItemService} from "../cartItem/cart-item.service";
import {map, Observable} from "rxjs";
import {OrderItem} from "../../models/order-item";
import {OrderRequest} from "../../models/order-request";
import {UserIdService} from "../user-id.service";
@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient ,private cartService: CartItemService , private userIdService:UserIdService) { }

  baseUrl = "http://localhost:8081/api/orders/";

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


  viewHistory(pageNumber: number , pageSize : number):Observable<any>
  {
    return this.http.get(this.baseUrl +"getMyOrders/" + pageNumber + "/" + pageSize)
      .pipe(map(response => response))
  }

  viewHistorySorted(pageNumber: number , pageSize : number , typeSorted: string):Observable<any>
  {
    return this.http.get(this.baseUrl +"getMyOrdersSorted/" + pageNumber + "/" + pageSize + "/" + typeSorted)
      .pipe(map(response => response))
  }


  viewMyApproval():Observable<any>
  {
    return this.http.get(this.baseUrl +"getMyApproval").pipe(map(response => response))
  }


  allPendingOrders():Observable<any>
  {
    return this.http.get(this.baseUrl +"getAllOrdersPending").pipe(map(response => response))
  }

  approveOrder(id: number):Observable<any>
  {
    return this.http.put(this.baseUrl +"approveOrder/" + id ,null).pipe(map(response => response))
  }

  rejectedOrder(id: number , message: string):Observable<any>
  {
    return this.http.put(this.baseUrl +"rejectedOrder/" + id + "/" + message,null).pipe(map(response => response))
  }


  viewAllHistory(pageNumber: number , pageSize : number):Observable<any>
  {
    return this.http.get<any>(this.baseUrl + "getAllOrders/" + pageNumber + "/" + pageSize)
      .pipe(map(response => response));
  }

  viewAllHistorySorted(pageNumber: number , pageSize : number , typeSorted: string):Observable<any>
  {
    return this.http.get<any>(this.baseUrl + "getAllOrdersSorted/" + pageNumber + "/" + pageSize + "/" + typeSorted).
    pipe(map(response => response));
  }

  filterMyOrdersHistoryFromDateToDate(pageNumber: number , pageSize : number , fromDate: string , toDate: string ,typeSorted: string):Observable<any>
  {
    return this.http.get<any>(this.baseUrl + "filterMyOrdersFromDateToDate/" + pageNumber + "/" + pageSize +"/" + fromDate + "/" + toDate + "/" + typeSorted )
      .pipe(map(response => response));
  }


  FilterAllOrdersHistoryFromDateToDate(pageNumber: number , pageSize : number , fromDate: string , toDate: string ,typeSorted : string)
  {
    return this.http.get<any>(this.baseUrl + "FilterAllOrdersFromDateToDate/" + pageNumber + "/" + pageSize +"/" + fromDate + "/" + toDate + "/" + typeSorted )
      .pipe(map(response => response));
  }


}
