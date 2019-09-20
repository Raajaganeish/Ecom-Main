import { Component, OnInit } from '@angular/core';
import { Payment } from 'src/app/class/payment';
import { PaymentService } from 'src/app/services/payment.service';
import { UserPayment } from 'src/app/class/user-payment';
import { CartService } from 'src/app/services/cart.service';
import { ShoppingCart } from 'src/app/class/shopping-cart';
import { ShippingAddress } from 'src/app/class/shipping-address';
import { CartItem } from 'src/app/class/cart-item';
import { OrderService } from 'src/app/service/order.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

 private orderPlaced:boolean=false;
  private payment:Payment = new Payment();
  private userPayment : UserPayment = new UserPayment();
  private shippingAddress:ShippingAddress=new ShippingAddress();
  cardType:string[]=[
    'Visa',
    'Master',
    'AmericanExpress',
    'Rupay',
  ];
  private loaded:boolean=false;
  private defaultSet:boolean=false;
  private defaultAddressSet:boolean=false;
  private sc:ShoppingCart=new ShoppingCart();

  private cartItemList:CartItem[]=[];
  constructor(private paymentService:PaymentService,private shoppingCartService:CartService,private orderService:OrderService,private router:Router) { }

  ngOnInit() {

    this.paymentService.getDefaultPayment().subscribe(
      res=>{this.userPayment=res.json();console.log(this.userPayment);this.loaded=true},
      err=>{console.log(err.text())}
    )
    this.shoppingCartService.getShoppingCart().subscribe(
      res=>{ this.sc = res.json(); this.loaded=true;},
      err=>{console.log(err.text())}
    )
    this.shoppingCartService.getAllCartItems().subscribe(res=>{this.cartItemList=res.json()})
  }

  onChange(ele:any)
  {
    console.log("Payment Change");

    if(ele.checked)
    {
      console.log(this.userPayment)
      // this.payment.id = this.userPayment.id;
      this.payment.holderName = this.userPayment.holderName;
      this.payment.cardType = this.userPayment.cardType;
      this.payment.cardName = this.userPayment.cardName;
      this.payment.cardNumber = this.userPayment.cardNumber;
      this.payment.expiryDate = this.userPayment.expiryDate;
      this.payment.expiryMonth = this.userPayment.expiryMonth;
      this.payment.ccv = this.userPayment.ccv;
      this.defaultSet=true;
    }
    else{
      this.payment = new Payment();
      this.defaultSet=false;
    }
  }

  onChangeAddress(ele:any)
  {
    console.log(ele)
    if(ele.checked)
    {
      // this.shippingAddress.id = this.userPayment.userBillAddress.id;
      this.shippingAddress.shippingAddressSt1 = this.userPayment.userBillAddress.addressSt1;
      this.shippingAddress.shippingAddressSt2 = this.userPayment.userBillAddress.addressSt2;
      this.shippingAddress.shippingAddressCity = this.userPayment.userBillAddress.addressCity;
      this.shippingAddress.shippingAddressCountry = this.userPayment.userBillAddress.addressCountry;
      this.shippingAddress.shippingAddressName = this.userPayment.userBillAddress.addressName;
      this.shippingAddress.shippingAddressZipCode = this.userPayment.userBillAddress.addressZipCode;
      this.defaultAddressSet = true;
    }
    else{
      this.shippingAddress = new ShippingAddress();
      this.defaultAddressSet = false;
    }
  }


  onPayment()
  {
    this.orderPlaced = true;
    console.log(this.payment)
    console.log(this.shippingAddress);
    this.orderService.checkout(this.shippingAddress,this.payment).subscribe(
      res=>{console.log(res.json());
        // this.orderService.order_data.next(res.json());
        swal.fire(
          {type: 'success',
          title: 'Order has been placed succesfully',
          showConfirmButton: false,
          timer: 3000,
        })
        this.router.navigate(['/myProfile/orderSummary'])},
      err=>{console.log(err.text())}
    )
  }

}
