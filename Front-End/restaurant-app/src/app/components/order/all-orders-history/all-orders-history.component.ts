import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../../../services/Order/order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-all-orders-history',
  templateUrl: './all-orders-history.component.html',
  styleUrls: ['./all-orders-history.component.css']
})
export class AllOrdersHistoryComponent implements OnInit {

  constructor(private orderService: OrderService, private router: Router) {}

  protected messageAr: string | null = null;
  protected messageEn: string | null = null;

  fromDate!: string;
  toDate!: string;

  allOrders: any[] = [];

  page: number = 1
  pageLength: number = 2;
  collectionSize: number = 100;

  sortField: string = 'createdDate';
  sortDirection: 'ASC' | 'DESC' = 'DESC';


  ngOnInit(): void
  {
    this.viewAllOrdersHistory();
  }

// ----------------- Sorting Methods -----------------
  applySorting() {
    this.page = 1; // reset pagination
      this.viewAllHistorySorted(this.sortDirection);
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

      this.FilterAllOrdersHistoryFromDateToDate(this.fromDate, this.toDate ,this.sortDirection);

  }

  viewAllOrdersHistory()
  {
    this.orderService.viewAllHistory(this.page ,this.pageLength).subscribe(res =>
    {
      this.allOrders = res.orders
      this.collectionSize = res.totalCount
    });
  }

  protected viewAllHistorySorted(typeSorted: string)
  {
    this.orderService.viewAllHistorySorted(this.page ,this.pageLength , typeSorted).subscribe(value =>{
      this.allOrders = value.orders;
      this.collectionSize = value.totalCount
    })
  }


  // ===== Filtered History =====
  protected FilterAllOrdersHistoryFromDateToDate(fromDate: string, toDate: string , typeSorted : string) {
    this.messageAr = null;
    this.messageEn = null;

    this.orderService.FilterAllOrdersHistoryFromDateToDate(this.page, this.pageLength, fromDate, toDate , typeSorted).subscribe(value =>
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






  goToProducts(): void
  {
    this.router.navigateByUrl('/products');
  }



  protected doPagination(event: number)
  {
    this.page = event ;
    this.viewAllOrdersHistory();
  }

  protected changeSize(event: Event)
  {
    this.pageLength = +(<HTMLInputElement>event.target).value ;
    this.viewAllOrdersHistory();
  }


  previousPage() {
    if(this.page > 1) this.page--;
    this.doPagination(this.page);
  }

  nextPage() {
    if(this.page < Math.ceil(this.collectionSize / this.pageLength)) this.page++;
    this.doPagination(this.page);
  }


}
