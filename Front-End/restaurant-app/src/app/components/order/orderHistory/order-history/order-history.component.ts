import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../../../../services/Order/order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {

  allOrders: any[] = [];

constructor(private orderService: OrderService ,private router: Router) { }

  ngOnInit(): void
  {
       this.viewHistory();
  }

  protected viewHistory()
  {
    this.orderService.viewHistory().subscribe(value => this.allOrders = value)
  }

  goToProducts(): void
  {
    this.router.navigateByUrl('/products');
  }
}
