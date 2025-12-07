import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductComponent } from './components/product/product.component';
import { TeamComponent } from "./components/team/team/team.component";
import { SignUpComponent } from "./components/sign-up/sign-up.component";
import { LoginComponent } from "./components/login/login.component";
import { AuthGuard } from "../services/guard/auth.guard";
import { loginSignUpGuard } from "../services/guard/login-sign-up.guard";
import { ContactInfoComponent } from "./components/contact/contact-info/contact-info.component";
import { CartItemComponent } from "./components/cartItem/cart-item/cart-item.component";
import {OrderService} from "../services/Order/order.service";
import {OrderComponent} from "./components/order/order.component";
import {OrderHistoryComponent} from "./components/order/orderHistory/order-history/order-history.component";
import {AddProductComponent} from "./components/product/addProduct/add-product/add-product.component";
import {RemoveProductComponent} from "./components/product/removeProduct/remove-product/remove-product.component";
import {UpdateProductComponent} from "./components/product/updateProduct/update-product/update-product.component";
import {AllOrdersHistoryComponent} from "./components/order/all-orders-history/all-orders-history.component";
import {UserDetailsComponent} from "./components/user-details/user-details.component";
import {
  ShowAllContactsComponent
} from "./components/contact/showAllContacts/show-all-contacts/show-all-contacts.component";
import {ShowMyContactComponent} from "./components/contact/show-my-contact/show-my-contact.component";

const routes: Routes = [
  // 1. المسارات المحمية بـ AuthGuard
  { path: 'products', component: ProductComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'addProduct', component: AddProductComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'removeProduct', component: RemoveProductComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'updateProduct/:id', component: UpdateProductComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'contact', component: ContactInfoComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'showMyContact', component: ShowMyContactComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'showAllContacts', component: ShowAllContactsComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'userDetails', component: UserDetailsComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'orderHistory', component: OrderHistoryComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'allOrderHistory', component: AllOrdersHistoryComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'cart', component: CartItemComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'order', component: OrderComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'products/category/:id', component: ProductComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'team', component: TeamComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },
  { path: 'search/:key', component: ProductComponent, canActivate: [AuthGuard], runGuardsAndResolvers: 'always' },

  // *** تم حذف المسار { path: 'products/category/1', ... } بناءً على طلبك ***

  // 2. مسارات تسجيل الدخول/الاشتراك (يجب وضعها قبل مسارات إعادة التوجيه العامة)
  { path: 'login', component: LoginComponent, canActivate: [loginSignUpGuard], runGuardsAndResolvers: 'always' },
  { path: 'signup', component: SignUpComponent, canActivate: [loginSignUpGuard], runGuardsAndResolvers: 'always' },

  // 3. مسارات إعادة التوجيه الختامية (يجب وضعها دائماً في النهاية)
  { path: '', redirectTo: '/products', pathMatch: 'full' },  // المسار الافتراضي
  { path: '**', redirectTo: '/products' },                   // أي مسار غير موجود
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
