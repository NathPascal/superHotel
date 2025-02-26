import { City } from "./city.model";

export class Hotel {
    id : number;
    name : string
    phone : string;
    address : string;
    stars : number;
    rooms : number;
    price : number;
    imageUrl : string;
    cityId : number | null;

    
  
    constructor(id:number,name:string,phone:string,address:string,stars:number,rooms:number,price:number,imageUrl:string, cityId: number){
      this.id = id;
      this.name = name
      this.phone = phone;
      this.address = address;
      this.stars = stars;
      this.rooms = rooms;
      this.price = price;
      this.imageUrl = imageUrl;
      this.cityId = cityId;
      }
    }