import { Injectable } from '@angular/core';
import {CartItem} from "../../models/cart-item";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CartItemService {

  constructor() { }

  cartItems: CartItem[] = [];

  private cartItemsSubject = new BehaviorSubject<CartItem[]>([]);

  cartItems$: Observable<CartItem[]> = this.cartItemsSubject.asObservable();


  private updateCartState(): void
  {
    this.cartItemsSubject.next(this.cartItems);
  }



  addToCart(product: any)
  {
    const cartItem = new CartItem();

    const existingItem = this.cartItems.find(item => item.id === product.id);

    if (existingItem)
    {
      // @ts-ignore
      existingItem.quantity++;
    }else {
      const cartItem: { id: any; name: any; price: any; imagePath: any; quantity: number ,description: any } = {
        id: product.id,
        name: product.name,
        price: product.price,
        imagePath: product.imagePath,
        description: product.description,
        quantity: 1
      };

      // @ts-ignore
      this.cartItems.push(cartItem);

    }

    this.updateCartState()

  }

// في ملف CartItemService.ts

  decreaseQuantity(product: any): void
  {
    const existingItemIndex = this.cartItems.findIndex(item => item.id === product.id);

    if (existingItemIndex > -1)
    {
      const itemToModify = this.cartItems[existingItemIndex];

      if (itemToModify.quantity! > 0)
      {
        itemToModify.quantity!--;

        if (itemToModify.quantity === 0)
        {
          this.cartItems.splice(existingItemIndex, 1);
        }

        this.updateCartState();
      }
    }
  }



  getCartItems(): any
  {
    return this.cartItems;
  }

 clearCartItems(): any
  {
     this.cartItems = [];
    this.updateCartState();
  }


  getTotalPrice(): number {
    return this.cartItems.reduce(
      (sum, item) => sum + item.price * item.quantity, 0
    );
  }

  getTotalQuantity(): number {
    return this.cartItems.reduce(
      (sum, item) => sum + item.quantity, 0
    );
  }

}
