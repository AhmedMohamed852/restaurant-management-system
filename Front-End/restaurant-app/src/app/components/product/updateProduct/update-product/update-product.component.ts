import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { ProductService } from "../../../../../services/product.service";

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

    product: any ;

    messageAr: string = "";
    messageEn: string = "";



  constructor(private router: Router, private productService: ProductService, private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
       const id: any = this.activatedRoute.snapshot.paramMap.get('id')

      this.productService.getProductById(id).subscribe(product =>
    {
      this.product = product;
    })
    }


  updateProduct()
  {
    const id: any = this.product.id;

    this.productService.updateProduct(this.product).subscribe(() =>
    {
      this.router.navigateByUrl("/products");
    } , errorResponse =>
    {
      this.messageAr = errorResponse.error.message_ar || errorResponse.error[0].message_ar;
      this.messageEn = errorResponse.error.message_en || errorResponse.error[0].message_en;

      setTimeout(() =>
      {
        this.messageAr ="";
        this.messageEn ="";
      } , 5000)
    })

  }

}
