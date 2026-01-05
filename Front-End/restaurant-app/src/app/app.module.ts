import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ProductComponent } from './components/product/product.component';
import { CategoryComponent } from './components/category/category.component';
import { TeamComponent } from './components/team/team/team.component';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import {AuthTokenInterceptor} from "../services/interseptor/auth-token.interceptor";
import {NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import { ContactInfoComponent } from './components/contact/contact-info/contact-info.component';
import { CartItemComponent } from './components/cartItem/cart-item/cart-item.component';
import { OrderComponent } from './components/order/order.component';
import { OrderHistoryComponent } from './components/order/orderHistory/order-history/order-history.component';
import { AddProductComponent } from './components/product/addProduct/add-product/add-product.component';
import { RemoveProductComponent } from './components/product/removeProduct/remove-product/remove-product.component';
import { UpdateProductComponent } from './components/product/updateProduct/update-product/update-product.component';
import {FormsModule} from "@angular/forms";
import { AllOrdersHistoryComponent } from './components/order/all-orders-history/all-orders-history.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { ShowAllContactsComponent } from './components/contact/showAllContacts/show-all-contacts/show-all-contacts.component';
import { ShowMyContactComponent } from './components/contact/show-my-contact/show-my-contact.component';
import { PendingOrdersComponent } from './components/order/My_pending-orders/pending-orders.component';
import { AllPendingOrdersComponent } from './components/order/all-pending-orders/all-pending-orders.component';


@NgModule({
  declarations: [
    AppComponent,
    CategoryComponent,
    HeaderComponent,
    FooterComponent,
    ProductComponent,
    TeamComponent,
    LoginComponent,
    SignUpComponent,
    ContactInfoComponent,
    CartItemComponent,
    OrderComponent,
    OrderHistoryComponent,
    AddProductComponent,
    RemoveProductComponent,
    UpdateProductComponent,
    AllOrdersHistoryComponent,
    UserDetailsComponent,
    ShowAllContactsComponent,
    ShowMyContactComponent,
    PendingOrdersComponent,
    AllPendingOrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbPaginationModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthTokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
