import { CartItem } from './cart-item';
import { ShippingAddress } from './shipping-address';


export class Order {

  public id:number;
  public orderDate:string;
  public shippingDate:string;
  // public shippingMethod:string;
  public orderId:string;
  public orderStatus:number;
  public orderTotal:number;
  public cartItemList:CartItem[];
  public shippingAddress:ShippingAddress;
}
