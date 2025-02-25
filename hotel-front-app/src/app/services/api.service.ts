import { Injectable } from '@angular/core';
import { Hotel } from '../models/hotel.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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
}
