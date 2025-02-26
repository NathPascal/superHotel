import { Hotel } from "./hotel.model";

export class City{
    id: number;
    name: string;
    hotels: Hotel[];

    constructor(id:number,name:string,hotels: Hotel[]){
        this.id = id;
        this.name = name;
        this.hotels = hotels;
        }
}