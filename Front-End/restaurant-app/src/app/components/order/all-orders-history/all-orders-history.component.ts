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

  messageAr: string = "";
  messageEn: string = "";

  allOrders: any[] = [];



  ngOnInit(): void
  {
    this.viewAllOrdersHistory();
  }

  viewAllOrdersHistory()
  {
    this.orderService.viewAllHistory().subscribe(res => this.allOrders = res);
  }


  goToProducts(): void
  {
    this.router.navigateByUrl('/products');
  }


}
