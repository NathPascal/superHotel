export class Hotel {
    id : number;
    name : string
    phone : string;
    address : string;
    stars : number;
    rooms : number;
    price : number;
    imageUrl : string;

    
  
    constructor(id:number,name:string,phone:string,address:string,stars:number,rooms:number,price:number,imageUrl:string){
      this.id = id;
      this.name = name
      this.phone = phone;
      this.address = address;
      this.stars = stars;
      this.rooms = rooms;
      this.price = price;
      this.imageUrl = imageUrl;
      }
    }