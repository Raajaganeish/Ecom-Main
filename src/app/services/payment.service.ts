import { Injectable } from '@angular/core';
import { Http,Headers } from '@angular/http';
import { UserPayment } from '../class/user-payment';
import { User } from '../class/user';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http:Http) { }

  AddNewPayment(payment:UserPayment)
  {
    let url = "http://localhost:8080/Profile/paymentAdd";
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.post(url,JSON.stringify(payment),{headers:header})
  }

  getAllPaymentList()
  {
    let url = "http://localhost:8080/Profile/getPaymentInfo";
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.get(url,{headers:header})
  }


  removePayment(id:number)
  {
    let url = "http://localhost:8080/Profile/paymentRemove";
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.post(url,id,{headers:header})
  }

  setDefaultPayment(paymentlist:UserPayment[])
  {
    let url = "http://localhost:8080/Profile/setDefault";
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.post(url,paymentlist,{headers:header})
  }

  getDefaultPayment()
  {
    let url = "http://localhost:8080/Profile/getDefault";
    let header : Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.get(url,{headers:header})
  }


}
