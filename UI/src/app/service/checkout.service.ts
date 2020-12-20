import { Injectable } from '@angular/core';
import { Http,Headers } from '@angular/http';
import { UserBillAddress } from '../class/user-bill-address';
import { UserPayment } from '../class/user-payment';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(private http:Http) { }

  checkout(billingAddress:UserBillAddress,payment:UserPayment,shippingMethod:string)
  {
    let url='http://localhost:8080/checkout/buy';
    let order = { 'billingAddress' :billingAddress,
                  'payment' : payment,
                  'shippingMethod' : shippingMethod,}
    let header:Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.post(url,order,{headers:header})
  }

  getUserOrder()
  {
    let url='http://localhost:8080/checkout/getOrder';
    let header:Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.get(url,{headers:header})
  }
}
