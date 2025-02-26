import { Injectable } from '@angular/core';
import { Hotel } from '../models/hotel.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { City } from '../models/city.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http:HttpClient) { }

  //Récupère tous les hôtels
  public getHotels(){
    return this.http.get<Hotel[]>(environment.host+"/hotels");
  }

  //Récupère un hôtel par ID
  public getHotelById(id: number): Observable<Hotel> {
    return this.http.get<Hotel>(`${environment.host}/hotels/${id}`);
  }

  //ajout d'un nouvel hôtel
  public addHotel(hotel: Hotel): Observable<Hotel> {
    return this.http.post<Hotel>(environment.host + "/hotels", hotel);
  }

 // Met à jour un hôtel existant
  public updateHotel(id: number, hotel: Hotel): Observable<Hotel> {
    return this.http.put<Hotel>(`${environment.host}/hotels/${id}`, hotel);
  }

  //Supprime un hôtel par ID
  public deleteHotel(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.host}/hotels/${id}`);
  }

  //Récupère toutes les villes
  public getCities(){
    return this.http.get<City[]>(environment.host+"/cities");
  }

  //Récupère une ville par ID
  public getCityById(id: number): Observable<City> {
    return this.http.get<City>(`${environment.host}/cities/${id}`);
  } 

  //ajout d'une nouvelle ville
  public addCity(city: City): Observable<City> {
    return this.http.post<City>(environment.host + "/cities", city);
  }

  // Met à jour une ville existant
  public updateCity(id: number, city: City): Observable<City> {
    return this.http.put<City>(`${environment.host}/cities/${id}`, city);
  }

  //Supprime une ville par ID
  public deleteCity(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.host}/cities/${id}`);
  }

  //Relation entre ville et hotel
  public getHotelsByCity(cityId: number): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(`${environment.host}/hotels?cityId=${cityId}`);
  }

}
