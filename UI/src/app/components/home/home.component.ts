import { Component, OnInit,ElementRef } from '@angular/core';
import { NgForm } from '@angular/forms';
import {Router} from '@angular/router';
import { UserLoginService } from '../../services/user-login.service';
import swal from 'sweetalert2';
import { ProfileInfoComponent } from '../profile-info/profile-info.component';
import { UserProfileService } from 'src/app/services/userservice.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  temp: NgForm;
  LoginForm: NgForm;
  private loggedIn: boolean = false;
  private credentials = {'username':'','password':''};
  constructor(private router: Router,private user_login_service:UserLoginService,private pfService:UserProfileService) { }

  ngOnInit() {

    this.user_login_service.CheckSession().subscribe(
      res=>{
        console.log(res)
        this.user_login_service.login_changed.next(true);
        this.router.navigate(['/myProfile/profileInfo']);
      },
      err=>{
        console.log("Need to login",err.text())
      },
    )
  }

  onSubmit(t: NgForm) {
    console.log("Registering ....",t.value.username);
    this.user_login_service.RegisterUser(t.value.username,t.value.email).subscribe(
      res=>{
        console.log(res)
        swal.fire(
          {type: 'success',
          title: 'Registered Successfully',
          showConfirmButton: false,
          timer: 1500,
        }
        )
      },
      errr=>{
        console.log(errr.text())
        let msg = errr.text()
        swal.fire(
          {type: 'error',
          title: msg,
          showConfirmButton: false,
          timer: 1500,
        })
      },
    )
  }


  onLogin(tform: NgForm) {

    this.credentials.username = tform.value.username;
    this.credentials.password = tform.value.password;
    this.user_login_service.sendCredentials(this.credentials.username,this.credentials.password).subscribe(
      res => {
        console.log(res.json());
        localStorage.setItem("xAuthToken",res.json()['token']);
        this.loggedIn=true;
        this.user_login_service.login_changed.next(true);
        this.router.navigate(['/myProfile/profileInfo']);
        this.pfService.LoggedUser(this.credentials.username);
      },
      error=>{
        console.log(error.text());
        swal.fire(
          {
            type:'error',
            title:'Invalid username or Bad Password',
            showConfirmButton: false,
            timer: 2500,
          }
        )
        this.loggedIn = false;
      }
    )

  }

  onForgotPassword(ele:HTMLInputElement)
  {
    console.log(ele.value);
    this.user_login_service.ForgotPassword(ele.value).subscribe(
      res=>{
        console.log(res);
        location.reload();
      },
      error=>{
        console.log(error.text);
        swal.fire(
          {type: 'error',
          title: error.text(),
          showConfirmButton: false,
          timer: 1500,
        })
      }
    )
  }

}
