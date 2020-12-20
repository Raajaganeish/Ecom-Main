import { Order } from './order';

export class ShippingAddress {

  public id:number;
  public shippingAddressName    : string;
  public shippingAddressSt1     : string;
  public shippingAddressSt2     : string;
  public shippingAddressCity    : string;
  public shippingAddressCountry : string;
  public shippingAddressZipCode : string;
  public order:Order;
}
