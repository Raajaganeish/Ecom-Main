import { Component, OnInit } from '@angular/core';
import { UserLoginService } from 'src/app/services/user-login.service';
import { Book } from 'src/app/class/book';
import { BookService } from 'src/app/services/book.service';
import { Router,ActivatedRoute, Params } from '@angular/router';
import swal from 'sweetalert2';
import { CartService } from 'src/app/services/cart.service';
@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  private bookId:number;
  private book:Book = new Book();
  private qtyList : number[] = [1,2,3,4,5,6,7,8,9];
  private qty:number;

  private isDisabled:boolean = false;

  private addedToCart:boolean

  constructor(private userLoginService:UserLoginService,private bkService:BookService,private router:Router,private ac_route:ActivatedRoute,private cartService:CartService) { }

  ngOnInit() {

    this.userLoginService.CheckSession().subscribe(
      res=>{
        this.userLoginService.login_changed.next(true);
      }
    );

    this.ac_route.params.forEach((params:Params)=>{

      this.bookId = Number.parseInt(params['id']);
    })

    this.bkService.getBookById(this.bookId).subscribe(
      (res)=>{
        this.book=res.json();
        console.log(this.book)
      },
      err=>{
        console.log(err.text());
      }
    )
  }

  onAddtoCart()
  {
    if(this.qty>0)
    {
      this.isDisabled = !this.isDisabled;
      console.log(this.qty,this.book);
      this.cartService.addItem(this.book.id,this.qty).subscribe(
        res=>{
          console.log(res.text())
          swal.fire(
            {type: 'success',
            title: res.text(),
            showConfirmButton: false,
            timer: 3000,
          })
        },
        err=>{console.log(err.text())}
      )
    }

    // this.userLoginService.badgeCountChange.next(true);
  }

}
