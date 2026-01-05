import {Component, OnInit} from '@angular/core';
import {CartItem} from "../../../../models/cart-item";
import {CartItemService} from "../../../../services/cartItem/cart-item.service";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../../../services/Order/order.service";
import {OrderDataService} from "../../../../services/order-data-service.service";
import {UserIdService} from "../../../../services/user-id.service";
import {UserDetailsService} from "../../../../services/userDetails/user-details.service";

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {

  constructor(
    private cartService: CartItemService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private orderService: OrderService,
    private orderDataService: OrderDataService,
    private userService: UserIdService,
    private userDetail: UserDetailsService
  ) {}

  messageEn: string = "";
  messageAr: string = "";

  showMessage: boolean = false;

  roles: string[] = [];
  userHasDetails: boolean = false;

  cartItems: CartItem[] = [];
  cartItemHelper: CartItem[] = [];

  page: number = 1;
  pageLength: number = 4;
  collectionSize: number = 0;

  ngOnInit(): void {
    this.roles = this.userService.getRoles();

    this.activatedRoute.paramMap.subscribe(() => {
      this.cartService.cartItems$.subscribe(items => {
        this.cartItems = items;
        this.collectionSize = this.cartItems.length;
        this.applyPaginationSlice();
      });
    });
  }

  applyPaginationSlice(): void {
    const startIndex = (this.page - 1) * this.pageLength;
    const endIndex = startIndex + this.pageLength;
    this.cartItemHelper = [...this.cartItems.slice(startIndex, endIndex)];
  }

  protected viewHistory() {
    this.router.navigateByUrl('/orderHistory');
  }

  doPagination(newPage: number) {
    this.page = newPage;
    this.applyPaginationSlice();
  }

  changeSize(event: Event) {
    this.pageLength = +(<HTMLSelectElement>event.target).value;
    this.page = 1;
    this.applyPaginationSlice();
  }

  increaseQuantity(item: CartItem) {
    this.cartService.addToCart(item);
  }

  decreaseQuantity(item: CartItem) {
    this.cartService.decreaseQuantity(item);
  }

  protected async confirmOrder() {

    const value = await this.userDetail.isUserHaseDetails().toPromise();
    this.userHasDetails = !value;

    if (this.userHasDetails) {
      this.messageEn = "must update Your Details";
      this.messageAr = "يجب تحديث البيانات";
      this.showMessage = true;
      await this.timeOut();
      this.showMessage = false;
      return;
    }

    this.orderService.submitOrder().subscribe({
      next: async (response) => {

        this.orderDataService.setOrderData(response);
        this.cartService.clearCartItems();

        if (response.totalPrice > 2000) {

          this.messageEn =
            'Your order is on hold as the total amount exceeds $2000 and is awaiting admin approval.';
          this.messageAr =
            'طلبك معلق حاليًا بسبب تجاوز قيمة الطلب 2000 دولار، وفي انتظار موافقة الإدارة.';

          this.showMessage = true;
          await this.timeOut();
          this.showMessage = false;

          await this.router.navigateByUrl('/PendingOrders');
          this.orderDataService.clearAllOrderData()
          return;
        }

        this.router.navigateByUrl("/order");
      },

      error: async (errorResponse) => {
        this.messageEn = errorResponse.error.message_en || "An unknown error occurred.";
        this.messageAr = errorResponse.error.message_ar || "حدث خطأ غير معروف.";
        this.showMessage = true;
        await this.timeOut();
        this.showMessage = false;
      }
    });
  }

  protected viewAllHistory() {
    this.router.navigateByUrl('/allOrderHistory');
  }

  isAdmin() {
    return this.roles.includes("ADMIN");
  }

  timeOut(): Promise<void> {
    return new Promise(resolve => {
      setTimeout(() => {
        this.messageAr = "";
        this.messageEn = "";
        resolve();
      }, 5000);
    });
  }
}
