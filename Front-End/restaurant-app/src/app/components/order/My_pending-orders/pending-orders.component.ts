import {Component, OnInit} from '@angular/core';
import {CartItemService} from "../../../../services/cartItem/cart-item.service";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../../../services/Order/order.service";
import {OrderDataService} from "../../../../services/order-data-service.service";
import {UserIdService} from "../../../../services/user-id.service";
import {UserDetailsService} from "../../../../services/userDetails/user-details.service";
import {CartItem} from "../../../../models/cart-item";

@Component({
  selector: 'app-My_pending-orders',
  templateUrl: './pending-orders.component.html',
  styleUrls: ['./pending-orders.component.css']
})
export class PendingOrdersComponent implements OnInit {

  messageEn: string = "";
  messageAr: string = "";


  constructor(

    private orderService: OrderService,

  ) {}

  myApproval: any[] = [];


  ngOnInit(): void
  {
        this.getMyApproval()
  }

  getMyApproval() {
    this.orderService.viewMyApproval().subscribe(
      response => {
        this.myApproval = response.map((order: any) => {

          // افحص الحالة أو الرسالة واضبط النص
          if (order.message === "SUCCESS") {
            order.displayMessage = '✅ تم تأكيد الطلب بنجاح.';
          } else if (order.status === 'REJECTED') {
            order.displayMessage =  " ❌ تم رفض الطلب ==> " + order.message ;
          } else {
            order.displayMessage = '⏳ الطلب معلق حاليًا وسيتم الرد عليك قريبًا.';
          }

          return order;
        });
      },
      errorResponse => {
        this.messageAr = errorResponse.error.message_ar;
        this.messageEn = errorResponse.error.message_en;

        setTimeout(() => {
          this.messageAr = '';
          this.messageEn = '';
        }, 5000);
      }
    );
  }


}
