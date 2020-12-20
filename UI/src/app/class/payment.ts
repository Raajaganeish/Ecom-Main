import { ShippingAddress } from './shipping-address';

export class Payment {

  public id : number;
  public holderName : string;
  public cardType : string;
  public cardName : string;
  public cardNumber : string;
  public expiryDate : string;
  public expiryMonth : string;
  public ccv : number;
  public default:boolean;
}
