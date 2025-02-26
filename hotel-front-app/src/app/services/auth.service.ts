import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn = false;
  username: string = '';

  constructor(private http : HttpClient) { }

  login(credentials: {username: string, password: string}): Observable<any>{
    return this.http.post(`${environment.host}/login`, credentials);
  }

  setToken(token: string): void {
    localStorage.setItem('access-token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('access-token');
  }

  logout(): void {
    localStorage.removeItem('access-token');
    this.isLoggedIn = false;
    this.username = '';
  }

}
