import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { OrderService } from "../../../../services/Order/order.service";

@Component({
  selector: 'app-all-pending-orders',
  templateUrl: './all-pending-orders.component.html',
  styleUrls: ['./all-pending-orders.component.css']
})
export class AllPendingOrdersComponent implements OnInit {

  messageEn: string = "";
  messageAr: string = "";
  myApproval: any[] = [];

  constructor(
    private orderService: OrderService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.getAllPendingOrders();
  }

  getAllPendingOrders(): void
  {
    this.orderService.allPendingOrders().subscribe(
      response => this.myApproval = response,
      errorResponse => {
        this.messageAr = errorResponse.error.message_ar;
        this.messageEn = errorResponse.error.message_en;
        setTimeout(() => { this.messageAr = ""; this.messageEn = ""; }, 5000);
      }
    );
  }

  approveOrder(code: number): void {
    this.myApproval = this.myApproval.map(order => {
      if(order.code === code) return { ...order, approved: true };
      return order;
    });
    this.cdr.detectChanges();

    this.orderService.approveOrder(code).subscribe({
      next: () => {
        this.myApproval = this.myApproval.filter(order => order.code !== code);
      },
      error: (errorResponse) => {
        this.messageAr = errorResponse.error.message_ar || 'حدث خطأ';
        this.messageEn = errorResponse.error.message_en || 'An error occurred';
        this.myApproval = this.myApproval.map(order => {
          if(order.code === code) return { ...order, approved: false };
          return order;
        });
        setTimeout(() => { this.messageAr = ''; this.messageEn = ''; }, 5000);
      }
    });
  }

  confirmReject(order: any): void
  {
    if (!order.rejectionMessage || order.rejectionMessage.trim() === '') {
      return;
  }

    order.rejected = true;
    order.showRejectBox = false;

    this.orderService.rejectedOrder(order.code, order.rejectionMessage).subscribe({
      next: () => {
        order.rejectionMessageSent = '❌ تم رفض الطلب: ' + order.rejectionMessage;
      },
      error: () => {
        order.rejected = false;
        order.rejectionMessage = '';
      }
    });
  }

  cancelReject(order: any): void {

  }

}
