import { Injectable } from '@angular/core';
import { Http,Headers } from '@angular/http';
import { ShippingAddress } from '../class/shipping-address';
import { Payment } from '../class/payment';
import { Order } from '../class/order';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  public order_data : Subject<Order> = new Subject<Order>();

  constructor(private http:Http) { }

  getAllOrders()
  {
    let url='http://localhost:8080/order/getAllOrder';
    let header:Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.get(url,{headers:header})
  }


  checkout(shippingAddress: ShippingAddress,
    	payment:Payment){
  		let url ='http://localhost:8080/order/checkout';
  		let order ={
  			"shippingAddress" : shippingAddress,
  			"payment" : payment,
  		}

    	let tokenHeader = new Headers({
    		'Content-Type' : 'application/json',
    		'x-auth-token' : localStorage.getItem("xAuthToken")
    	});
    	return this.http.post(url, order, {headers: tokenHeader});
    }


}
