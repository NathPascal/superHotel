import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css'],
  template: `<button (click)="logout()">Logout</button>`
})
export class LogoutComponent {

  constructor(private authService: AuthService, private router: Router) { }

    ngOnInit() {
        this.authService.isLoggedIn = false;
        localStorage.setItem("access-token", '');
        localStorage.setItem("username", '');
        localStorage.setItem("password", '');

        this.authService.logout();
        this.router.navigateByUrl('/hotels');
    }


}
