import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string = '';
    password: string = '';

    constructor(private authService: AuthService, private router: Router) { }

    login() {
        this.authService.login({ username: this.username, password: this.password }).subscribe(
            response => {
                console.log('Connexion réussie', response);
                this.authService.setToken(response['access-token']);
                localStorage.setItem("username", this.username);
                //localStorage.setItem("password", this.password);
                this.authService.isLoggedIn = true;
                this.authService.username = this.username;
                this.router.navigateByUrl("/hotels");
            }, error => {
                console.error('échec connexion', error);
                this.authService.isLoggedIn = false;
            }
        )
    };

}
