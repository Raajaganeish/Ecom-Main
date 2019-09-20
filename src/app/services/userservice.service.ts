import { Injectable } from '@angular/core';
import { Http,Headers } from '@angular/http';
import { Subject } from 'rxjs';
import { User } from '../class/user';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

 user:string;
  constructor(private http:Http) { }

  LoggedUser(user:string){
    this.user = user;
  }

  getCurrentUser()
  {
      let url = 'http://localhost:8080/Profile/currentUser';
      let header = new Headers({
        'Content-Type':'application/json',
         'x-auth-token':localStorage.getItem('xAuthToken'),
      });

      return this.http.get(url,{headers:header})

  }

  updateUser(user:User)
  {
    let url = 'http://localhost:8080/Profile/updateUser';
    let header = new Headers({
      'Content-Type':'application/json',
       'x-auth-token':localStorage.getItem('xAuthToken'),
    });
     let data = {
      'id':user.id,
      'firstname':user.firstname,
      'lastname':user.lastname,
      'username':user.username,
      'email':user.email,
      'phone':user.phone,
    }
    return this.http.post(url,data,{headers:header});
  }
}
