import { Component, OnInit } from '@angular/core';
import { UserLoginService } from 'src/app/services/user-login.service';
import { BookService } from 'src/app/services/book.service';
import { Book } from 'src/app/class/book';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  private filterQuery:string="";
  private bookList:Book[]=[];
  private bookCount:number=0;
  constructor(private userLoginService:UserLoginService,private bookService:BookService,private router:Router) { }

  ngOnInit() {
    this.userLoginService.CheckSession().subscribe(
      res=>{
        this.userLoginService.login_changed.next(true);
        console.log(res.text())
      },
      err=>{console.log(err.text())}
    )

    this.getAllBooks()
  }
  getAllBooks()
  {
    this.bookService.GetAllBooks().subscribe(
      res=>{
        console.log(res.json())
        this.bookList=res.json();
      },
      err=>{
        console.log(err.text())
      }
    )
  }

  onAddtoCart(event,b1)
  {
    event.target.innerText="Added"
    b1.disabled=true;
    this.bookCount++;
  }

  onSelectBook(bk:Book)
  {
    console.log(bk)
    this.router.navigate(['myProfile/bookDetail',bk.id]);
  }
}
