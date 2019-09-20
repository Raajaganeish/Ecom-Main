import { Injectable } from '@angular/core';
import { Http,Headers } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:Http) { }

  GetAllBooks()
  {
    let url='http://localhost:8080/book/getAll';
    let header:Headers = new Headers(
      {
        'Content-Type':'application/json',
        'x-auth-token':localStorage.getItem('xAuthToken'),
      }
    )
    return this.http.get(url,{headers:header})
  }


  getBookById(index:number)
  {
    console.log("Executing Ajax GetBookById Call");
    let url = "http://localhost:8080/book/getBook/"+index;

    let headers = new Headers ({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url,{headers: headers})
  }
}
