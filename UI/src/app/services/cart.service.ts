import { Injectable } from '@angular/core';
import { Http,Headers } from '@angular/http';
import { UserLoginService } from './user-login.service';


@Injectable({
  providedIn: 'root'
})
export class CartService {

  private count:number=0;
  constructor(private http:Http,private userLoginService:UserLoginService) { }

  addItem(id:number,qty:number)
  {
    let url = "http://localhost:8080/cart/addItem";
    let cartItem = { 'bookId': id,  'qty':qty   };
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )

    return this.http.post(url, cartItem, {headers:header})
  }

  getAllCartItems()
  {
    let url = "http://localhost:8080/cart/getAllCartItems";
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.get(url,{headers:header})
  }

  getShoppingCart()
  {
    let url = "http://localhost:8080/cart/getShoppingCart";
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.get(url,{headers:header})
  }

  updateCartItem(cartItemId: number,qty: number)
  {
    let url = "http://localhost:8080/cart/updateCart";
    let cartItem = { 'cartItemId': cartItemId,  'qty':qty   }
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.post(url,cartItem,{headers:header})
  }


  removeCartItem(id: number)
  {
    let url = "http://localhost:8080/cart/remove";
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.post(url,id,{headers:header})
  }
  
  removeAllCartItem()
  {
    let url = "http://localhost:8080/cart/removeAll";
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.get(url,{headers:header})
  }
}
