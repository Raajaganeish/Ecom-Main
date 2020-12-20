import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserLoginService } from 'src/app/services/user-login.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @ViewChild('SearchTitle') tForm:NgForm;
  private count:number=0;
  loggedIn:boolean = false;
  constructor(private router:Router,private user_login_service:UserLoginService) { }

  ngOnInit() {

    this.user_login_service.login_changed.subscribe(
      (data:boolean)=>{this.loggedIn=data;}
    )

    this.user_login_service.badgeCountChange.subscribe(
      data=>{this.count=data}
    )

  }

  onSubmit()
  {
    console.log(this.tForm.value);
  }

  onInput(event:any)
  {
    console.log(event);
  }

  onLogin()
  {
    console.log("asdhlhksd");
    this.loggedIn = !this.loggedIn;
  }

  onLogout()
  {
    this.user_login_service.LogoutUser().subscribe(
      res=>{
        console.log("logged out!!")
        this.user_login_service.login_changed.next(false);
        this.router.navigate(["/home"]);
      },
      err=>{
        console.log(err.text());
      }
    );
  }

}
