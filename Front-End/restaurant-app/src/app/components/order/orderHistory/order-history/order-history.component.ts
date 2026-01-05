import { Component, OnInit } from '@angular/core';
import { OrderService } from "../../../../../services/Order/order.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {

  fromDate!: string;
  toDate!: string;

  allOrders: any[] = [];

  page: number = 1;
  pageLength: number = 2;
  collectionSize: number = 100;

  sortDirection: 'ASC' | 'DESC' = 'DESC';

  protected messageAr: string | null = null;
  protected messageEn: string | null = null;

  constructor(private orderService: OrderService, private router: Router) { }

  ngOnInit(): void {
    this.viewHistory();
  }

  // ===== Sorting =====
  applySorting() {
    this.page = 1; // reset pagination

      this.viewHistorySorted(this.sortDirection);
  }

  // ===== Filter =====
  applyFilter() {
    this.page = 1; // reset pagination
    if (!this.fromDate) {
      this.messageEn = 'Please select start date.';
      this.messageAr = 'من فضلك اختر تاريخ البداية.';
      return;
    }

    if (!this.toDate) {
      this.toDate = this.fromDate;
    }

    if (new Date(this.toDate) < new Date(this.fromDate)) {
      this.messageEn = 'End date cannot be before start date.';
      this.messageAr = 'تاريخ النهاية لا يمكن أن يكون قبل تاريخ البداية.';
      return;
    }

      this.filterMyOrdersHistoryFromDateToDate(this.fromDate, this.toDate , this.sortDirection);
  }

  // ===== Basic History =====
  protected viewHistory() {
    this.messageAr = null;
    this.messageEn = null;

    this.orderService.viewHistory(this.page, this.pageLength).subscribe({
      next: (value: any) => {
        this.allOrders = value.orders || [];
        this.collectionSize = value.totalCount || 0;

        if (!this.allOrders.length) {
          this.messageEn = value.message_en || null;
          this.messageAr = value.message_ar || null;
        }
      },
      error: () => {
        this.messageEn = 'Something went wrong. Please try again.';
        this.messageAr = 'حدث خطأ، حاول مرة أخرى.';
      }
    });
  }

  protected viewHistorySorted(typeSorted: string) {
    this.messageAr = null;
    this.messageEn = null;

    this.orderService.viewHistorySorted(this.page, this.pageLength , typeSorted).subscribe(value =>
    {
        this.allOrders = value.orders || [];
        this.collectionSize = value.totalCount || 0;

        if (!this.allOrders.length) {
          this.messageEn = value.message_en || null;
          this.messageAr = value.message_ar || null;
        }
      },
      errorResponse =>
      {
        this.messageAr = errorResponse.error.message_ar;
        this.messageEn = errorResponse.error.message_en;

        setTimeout(() =>
        {
          this.messageAr ="";
          this.messageEn ="";
        } , 5000)
      });
  }



  // ===== Filtered History =====
  protected filterMyOrdersHistoryFromDateToDate(fromDate: string, toDate: string , typeSorted: string) {
    this.messageAr = null;
    this.messageEn = null;

    this.orderService.filterMyOrdersHistoryFromDateToDate(this.page, this.pageLength, fromDate, toDate ,typeSorted).subscribe(value =>
      {
        this.allOrders = value.orders || [];
        this.collectionSize = value.totalCount || 0;

        // عرض رسالة السيرفر إذا موجودة
        if (!this.allOrders.length) {
          this.messageEn = value.message_en || 'No orders found for selected date range.';
          this.messageAr = value.message_ar || 'لا توجد طلبات في الفترة المحددة.';
        }
      },
      errorResponse =>
      {
        this.allOrders = [];
        this.messageAr = errorResponse.error.message_ar;
        this.messageEn = errorResponse.error.message_en;

        setTimeout(() =>
        {
          this.messageAr ="";
          this.messageEn ="";
        } , 5000)
      });
  }


  // ===== Navigation =====
  goToProducts(): void {
    this.router.navigateByUrl('/products');
  }

  // ===== Pagination =====
  protected doPagination(event: number) {
    this.page = event;
    this.viewHistory();
  }

  protected changeSize(event: Event) {
    this.pageLength = +(<HTMLInputElement>event.target).value;
    this.viewHistory();
  }

  previousPage() {
    if (this.page > 1) this.page--;
    this.doPagination(this.page);
  }

  nextPage() {
    if (this.page < Math.ceil(this.collectionSize / this.pageLength)) this.page++;
    this.doPagination(this.page);
  }
}
