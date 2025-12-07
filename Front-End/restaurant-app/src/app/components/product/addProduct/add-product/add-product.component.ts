import {Component, OnInit} from '@angular/core';
import {AuthGuard} from "../../../../../services/guard/auth.guard";
import {ProductService} from "../../../../../services/product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  messageAr: string = "";
  messageEn: string = "";


  constructor(private productService: ProductService ,private router: Router) { }



    ngOnInit(): void
    {

    }

    addProduct(name: string , imagePath: string ,description:string ,categoryName:string ,price:number)
    {
      this.productService.addProduct(name ,imagePath ,description ,categoryName ,price).subscribe(() =>
      {
        this.router.navigateByUrl("/products");
      } ,
        errorResponse =>
        {
          this.messageAr = errorResponse.error.message_ar ||errorResponse.error[0].message_ar ;
          this.messageEn = errorResponse.error.message_en || errorResponse.error[0].message_en;

          setTimeout(() =>
          {
            this.messageAr ="";
            this.messageEn ="";
          } , 5000)
        }
      )
    }



}
