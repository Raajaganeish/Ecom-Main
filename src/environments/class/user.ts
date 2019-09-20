import { UserPayment } from './user-payment';

export class User {

  public id:number;
  public username:string;
  public password:string;
  public firstname:string;
  public lastname:string;
  public email:string;
  public phone:string;
  public enabled:string;
  public userPayments:UserPayment[];
}
