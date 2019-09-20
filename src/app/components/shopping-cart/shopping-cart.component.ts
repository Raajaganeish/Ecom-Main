import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Book } from 'src/app/class/book';
import { ShoppingCart } from 'src/app/class/shopping-cart';
import { CartItem } from 'src/app/class/cart-item';
import { CartService } from 'src/app/services/cart.service';
import { UserLoginService } from 'src/app/services/user-login.service';


@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  private cartItemList:CartItem[] = [];
  private qtyList : number[] = [1,2,3,4,5,6,7,8,9];
  private shoppingCart:ShoppingCart = new ShoppingCart();
  constructor(private cartService:CartService,private router:Router,private userLoginService:UserLoginService) { }

  ngOnInit() {

    this.userLoginService.CheckSession().subscribe(
      res=>{
        this.userLoginService.login_changed.next(true);
      }
    );

    this.cartService.getAllCartItems().subscribe(
      res=>{ console.log(res.json());this.cartItemList=res.json()},
      err=>{console.log(err.text())}
    )

    this.getGrandTotal();
  }

  onUpdate(ct:CartItem)
  {
    console.log(ct);
    this.cartService.updateCartItem(ct.id,ct.qty).subscribe(
      res=>{console.log(res.text());location.reload()},
      err=>{console.log(err.text())}
    )
  }

  onDelete(id:number)
  {
    console.log(id)
    this.cartService.removeCartItem(id).subscribe(
      res=>{console.log(res);location.reload();},
      err=>{console.log(err)}
    )
  }

  getGrandTotal()
  {
    this.cartService.getShoppingCart().subscribe(
      res=>{
        console.log(res.json())
        this.shoppingCart = res.json();
      },
      err=>{console.log(err.text())}
    )
  }
  
  DeleteAll()
  {
	  console.log("Delete All Function")
	  this.cartService.removeAllCartItem().subscribe(
	  res=>{console.log(res)},
	  err=>{console.log(err)},
	  );
  }

}
