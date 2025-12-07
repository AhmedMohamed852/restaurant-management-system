import { Component } from '@angular/core';
import {ProductService} from "../../../../../services/product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-remove-product',
  templateUrl: './remove-product.component.html',
  styleUrls: ['./remove-product.component.css']
})
export class RemoveProductComponent {

  constructor(private productService: ProductService ,private router: Router) { }

  messageAr: string = "";
  messageEn: string = "";


  deleteProduct(name: string)
  {
      this.productService.deleteProduct(name).subscribe(() =>
      {
        this.router.navigateByUrl("/products");
      } , errorResponse =>
      {
        this.messageAr = errorResponse.error.message_ar ;
        this.messageEn = errorResponse.error.message_en ;

        setTimeout(() =>
        {
          this.messageAr ="";
          this.messageEn ="";
        } , 5000)
      });
  }
}
