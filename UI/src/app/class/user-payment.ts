import { UserBillAddress } from './user-bill-address';

export class UserPayment {

    public id : number;
    public holderName : string;
    public cardType : string;
    public cardName : string;
    public cardNumber : string;
    public expiryDate : string;
    public expiryMonth : string;
    public ccv : number;
    public default:boolean;
    public userBillAddress:UserBillAddress;


}
