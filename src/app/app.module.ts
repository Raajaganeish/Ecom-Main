import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {DataTableModule} from "angular-6-datatable";

import { FormsModule } from '@angular/forms';

import {MatButtonModule, MatCheckboxModule, MatInputModule} from '@angular/material';
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableModule} from '@angular/material/table';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatBadgeModule} from '@angular/material/badge';
import {MatIconModule} from '@angular/material/icon'
import {MatListModule} from '@angular/material/list';
import {MatProgressBarModule} from '@angular/material/progress-bar';


import {MatStepperModule} from '@angular/material/stepper';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import {Routes, RouterModule} from '@angular/router';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import {HttpModule} from '@angular/http';

import { UserLoginService } from './services/user-login.service';

import { AccountInfoComponent } from './components/account-info/account-info.component';
import { ProfileInfoComponent } from './components/profile-info/profile-info.component';
import { UserProfileService } from './services/userservice.service';
import { PaymentService } from './services/payment.service';
import { DataFilterPipe } from './Pipe/data-filter.pipe';
import { BookDetailComponent } from './components/book-detail/book-detail.component';
import { CartService } from './services/cart.service';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';
import { OrderComponent } from './components/order/order.component';
import { OrderService } from './service/order.service';
import { CheckoutService } from './service/checkout.service';
import { OrderSummaryComponent } from './components/order-summary/order-summary.component';



const route: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: 'full',
  },
  {
    path: 'home', component: HomeComponent,
  },
  {
    path: 'myProfile', component: MyProfileComponent,
    // children :[
    //   { path:'profileInfo', component: ProfileInfoComponent},
    // ]
  },
  { path:'myProfile/profileInfo', component: ProfileInfoComponent},
  { path:'myProfile/bookDetail/:id', component:BookDetailComponent},
  { path:'myProfile/cart', component:ShoppingCartComponent},
  { path:'myProfile/order', component:OrderComponent},
  { path:'myProfile/orderSummary', component:OrderSummaryComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    MyProfileComponent,
    AccountInfoComponent,
    ProfileInfoComponent,
    DataFilterPipe,
    BookDetailComponent,
    ShoppingCartComponent,
    OrderComponent,
    OrderSummaryComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    DataTableModule,
    MatButtonModule,
    MatCheckboxModule,
    MatRadioModule,
    MatSelectModule,
    MatFormFieldModule,
    MatCardModule,
    MatSlideToggleModule,
    MatBadgeModule,
    MatIconModule,
    MatTableModule,
    MatInputModule,
    MatTabsModule,
    MatStepperModule,
    MatProgressBarModule,
    FormsModule,
    HttpModule,
    MatListModule,
    RouterModule.forRoot(route),
  ],
  providers: [UserLoginService,UserProfileService,PaymentService,CartService,OrderService,CheckoutService],
  bootstrap: [AppComponent]
})
export class AppModule { }
