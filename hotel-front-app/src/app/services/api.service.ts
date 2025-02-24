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

  public getHotels(){
    return this.http.get<Hotel[]>(environment.host+"/hotels");
  }

  public getHotelById(id: number): Observable<Hotel> {
    return this.http.get<Hotel>(`${environment.host}/hotels/${id}`);
  }
}
