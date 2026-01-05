import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {OrderDataService} from "../../../services/order-data-service.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor( private router: Router ,private orderDataService: OrderDataService ) { }

  orderResponseData : any;



  ngOnInit()
  {
      this.orderResponse()
  }

  orderResponse()
  {

    this.orderResponseData = this.orderDataService.getOrderData();

    if(this.orderResponseData)
    {
      this.orderDataService.clearOrderData()
    }

  }

  protected action() {
    this.router.navigateByUrl('/products');
  }

}
