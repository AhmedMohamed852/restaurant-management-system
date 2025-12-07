import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../../services/product.service";
import {Product} from "../../../models/product";
import {ActivatedRoute, Router} from "@angular/router";
import {CartItemService} from "../../../services/cartItem/cart-item.service";
import {UserIdService} from "../../../services/user-id.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit  {

    products: Product[] = [];

  roles: string[] = [];

  messageAr: string = "";
  messageEn: string = "";

  page: number = 1
  pageLength: number = 10;
  collectionSize: number = 100;

    constructor(private productService: ProductService , private activatedRoute: ActivatedRoute ,
              private cartItemService: CartItemService , private router: Router  ,private userService: UserIdService) {}



    ngOnInit()
    {
      this.roles = this.userService.getRoles();
      this.activatedRoute.paramMap.subscribe(params =>
     {
       this.getAllProducts()
     });

    }


  getAllProducts() {
    this.activatedRoute.paramMap.subscribe(params => {

      const idParam = params.get('id');
      const id = idParam ? +idParam : null;

      const key = params.get('key');

      if (key) {
        this.search(key);
        return;
      }

      if (id !== null) {
        this.getProductsByCategoryId(id);
        return;
      }

      this.getProducts();
    });
  }


  getProducts(): void
    {
      this.messageAr = '';
      this.messageEn = '';

      this.productService.getAllProduct(this.page , this.pageLength).
      subscribe(Value =>
      {
        this.collectionSize = Value.totalProducts
        this.products = Value.products
      })

    }


  getProductsByCategoryId(id: number): void
    {
      this.messageAr = '';
      this.messageEn = '';
      this.productService.getAllProductByCategoryId(id , this.page , this.pageLength).subscribe( Value =>
      {
        this.products = Value.products;
        this.collectionSize = Value.totalProducts ;
      } ,
        errorResponse =>
        {
          this.products = [];
          this.messageAr = errorResponse.error.message_ar;
          this.messageEn = errorResponse.error.message_en;
        })
    }


    search(key: string): void{
      this.productService.getProductBySearch(key ,this.page , this.pageLength).subscribe( Value => {
        this.messageAr = '';
        this.messageEn = '';

        this.products = Value.products;
        this.collectionSize = Value.totalProducts ;
      } ,
        errorResponse =>
        {
          this.products = [];
          this.messageAr = errorResponse.error.message_ar;
          this.messageEn = errorResponse.error.message_en;
        })
    }




  protected doPagination(event: number)
  {
    this.page = event ;
    this.getAllProducts();
  }

  protected changeSize(event: Event)
  {
    this.pageLength = +(<HTMLInputElement>event.target).value ;
    this.getAllProducts();
  }


  deletemessages() : void
  {
    this.messageAr = '';
    this.messageEn = '';
  }

  protected readonly Product = Product;


 addToCart(product: any)
  {
   this.cartItemService.addToCart(product)
  }


  updateProduct(product: any)
  {
    this.router.navigate(['/updateProduct', product.id]);
  }

  isAdmin()
  {
    return this.roles.includes("ADMIN");
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

