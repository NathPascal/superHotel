import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-details-hotel',
  templateUrl: './details-hotel.component.html',
  styleUrls: ['./details-hotel.component.css']
})


export class DetailsHotelComponent implements OnInit {
  hotel: Hotel | undefined;

  constructor(private router: Router, private apiService: ApiService) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.getHotelDetails();
      }
    });
  }

  ngOnInit(): void {
    this.getHotelDetails();
  }

  getHotelDetails(): void {
    const urlSegments = this.router.url.split('/');
    const id = urlSegments[urlSegments.length - 1];
    console.log('ID récupéré :', id);
    if (id) {
      this.apiService.getHotelById(+id).subscribe({
        next: (hotel) => {
          console.log('Données de l\'hôtel :', hotel);
          this.hotel = hotel;
        },
        error: (err) => {
          console.log('Erreur lors de la récupération des détails', err);
        }
      });
    }
  }
}
