import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserProfileService } from 'src/app/services/userservice.service';
import { User } from 'src/app/class/user';
import { NgForm } from '@angular/forms';
import { UserPayment } from 'src/app/class/user-payment';
import { UserBillAddress } from 'src/app/class/user-bill-address';
import { PaymentService } from 'src/app/services/payment.service';
import { UserLoginService } from 'src/app/services/user-login.service';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent implements OnInit {

  private user:User = new User();
  private userPayment:UserPayment = new UserPayment();
  private userBilling : UserBillAddress = new UserBillAddress();
  private PaymentList:UserPayment[]= [];
  private tabIndex:number;
  defaultPaymentId:number=0;
  private CardDetailsUpdated:boolean;
  private BillingAddressUpdated:boolean;

  cardType:string[]=[
    'Visa',
    'Master',
    'AmericanExpress',
    'Rupay',
  ]
//uUmtz2
  constructor(private router:Router,private user_Service:UserProfileService,private PaymentService:PaymentService,private userLoginService:UserLoginService) { }


  ngOnInit() {
    this.CardDetailsUpdated=false;
    this.userLoginService.CheckSession().subscribe(
      res=>{console.log(res);this.userLoginService.login_changed.next(true);}
    )
    this.user_Service.getCurrentUser().subscribe(
      res=>{
        this.user = res.json();
         console.log(this.user.userPayments);
         this.PaymentList=this.user.userPayments;
      },
      err=>{
        console.log(err.text());
      }
    )

    this.getAllPaymentList();


  }

  updateProfile(profileForm:NgForm)
  {
    console.log(this.user);
    this.user_Service.updateUser(this.user).subscribe(
      res=>{
        console.log(res)
		location.reload();
      },
      err=>{
        console.log(err.text())
      }
    )
  }
  selectedBillingChange(val:any)
  {
    this.tabIndex=val;
  }

  setDefaultPayment()
  {
    console.log(this.PaymentList);
    this.PaymentService.setDefaultPayment(this.PaymentList).subscribe(
      res=>{console.log(res);},
      err=>{console.log(err.text())}
    )
  }

  getAllPaymentList()
  {
    this.PaymentService.getAllPaymentList().subscribe(
      res=>{console.log(res.json());
      this.PaymentList=res.json()
    },
      err=>{console.log(err.text())}
    )
    console.log("data")


  }

  onUpdate(payment_obj:UserPayment)
  {
    this.tabIndex=1;
    console.log(payment_obj.userBillAddress)
    this.userPayment=payment_obj;
    this.userBilling=payment_obj.userBillAddress;
  }

  OnNewPayment()
  {
    this.userPayment.userBillAddress=this.userBilling;
    console.log(this.userPayment);
    this.CardDetailsUpdated=true;
    this.PaymentService.AddNewPayment(this.userPayment).subscribe(
      res=>{console.log(res)},
      err=>{console.log(err.text())}
    )
    location.reload();
  }

  onDelete(id:number)
  {
    console.log("Removing")
    this.PaymentService.removePayment(id).subscribe(
      res=>{console.log(res)},
      err=>{console.log(err.text())}
    )
  }



  defaultChanged(event:any,payment:UserPayment)
  {
    payment.default=!payment.default;

    console.log(payment.default);

  }





}
