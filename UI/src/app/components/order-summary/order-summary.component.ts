import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/service/order.service';
import { Order } from 'src/app/class/order';

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent implements OnInit {

  private dataLoaded:boolean=false;
  private orderList:Order[] = [];
  constructor(private orderService:OrderService) { }

  ngOnInit() {
    this.orderService.getAllOrders().subscribe(
      res=>{this.orderList = res.json();console.log(this.orderList);this.dataLoaded=true;this.orderList.forEach(x=>{x.orderDate=new Date(x.orderDate).toString()})},
      err=>{console.log(err.text())}
    )
  }



}
