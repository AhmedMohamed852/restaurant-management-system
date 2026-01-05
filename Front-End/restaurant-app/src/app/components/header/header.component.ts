import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../../services/product.service";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Category} from "../../../models/category";
import {AuthService} from "../../../services/security/auth.service";
import {UserIdService} from "../../../services/user-id.service";
import {CartItemService} from "../../../services/cartItem/cart-item.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  roles: string[] = [];

  totalPrice = 0;
  totalQuantity = 0;

  constructor(private router: Router , private  authService: AuthService ,private userService: UserIdService ,private cartService: CartItemService) {}

  ngOnInit(): void
  {
    this.cartService.cartItems$.subscribe(items =>
    {
      this.totalQuantity = items.reduce((sum, i) => sum + i.quantity, 0);
      this.totalPrice = items.reduce((sum, i) => sum + i.price * i.quantity, 0);
    });

       this.roles = this.userService.getRoles();
    }



  search(value: string) {
    if (value.trim() !== "" && value.trim() !== "1") {
      this.router.navigateByUrl("/search/" + value);
    }else   if (value.trim() === "1") {
      this.router.navigateByUrl("/products/category/" + value);
    }else {
      this.router.navigateByUrl("/products");
    }
  }


  protected readonly Category = Category;

  protected logout()
  {
    this.authService.logout();
    this.router.navigateByUrl("/login");
  }

  isUserLogin()
  {
    return  sessionStorage.getItem("token") &&
     sessionStorage.getItem("token") !== undefined &&
     sessionStorage.getItem("token") !== null ;
  }


  protected isAdmin()
  {
    return  this.roles.includes("ADMIN");
  }

  protected addProduct()
  {
    this.router.navigateByUrl("/addProduct");
  }

  protected removeProduct()
  {
    this.router.navigateByUrl("/removeProduct");
  }

  protected showAllContacts()
  {
    this.router.navigateByUrl("/showAllContacts");
  }

  protected showAllPendingOrders()
  {
    this.router.navigateByUrl("/allPendingOrders");
  }
}
