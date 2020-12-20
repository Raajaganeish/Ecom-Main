import { Injectable } from '@angular/core';
import { Http,Headers } from '@angular/http';
import { Observable, Subject} from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserLoginService {

  public login_changed : Subject<boolean> = new Subject<boolean>();
  public badgeCountChange:Subject<number> = new Subject<number>();
  constructor(private  http: Http) { }


  sendCredentials(username: string,password: string)
  {
     let url = 'http://localhost:8080/token';
     let encodedCred = btoa(username+":"+password);
     let basicEncode = "Basic "+encodedCred;
     let header = new Headers({
        'Content-Type':'application/x-www-form-urlencoded',
        'Authorization':basicEncode
     });

     return this.http.get(url,{headers:header});
  }


  CheckSession()
  {
      let url = 'http://localhost:8080/checkSession';
      let header = new Headers({
         'x-auth-token':localStorage.getItem('xAuthToken'),
      });

      return this.http.get(url,{headers:header});
  }



  LogoutUser()
  {
      let url = 'http://localhost:8080/logout_user';
      let header = new Headers({
         'x-auth-token':localStorage.getItem('xAuthToken'),
      });

      return this.http.post(url,'',{headers:header});
  }

  ForgotPassword(email:string)
  {
    let url = 'http://localhost:8080/forgot_Password';
    let header = new Headers({
       'Content-Type':'application/json',
    });

    return this.http.post(url,JSON.stringify({'email':email}),{'headers':header});
  }

  RegisterUser(username:string,email:string)
  {
    let url = 'http://localhost:8080/register/new_user';
    let user_details = {'username':username,'email':email};
    let header = new Headers({
       'Content-Type':'application/json',
    });
    return this.http.post(url,user_details,{'headers':header});

  }







}
