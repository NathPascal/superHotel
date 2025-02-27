import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-details-hotel',
  templateUrl: './details-hotel.component.html',
  styleUrls: ['./details-hotel.component.css']
})


export class DetailsHotelComponent implements OnInit {
  hotel: Hotel | undefined;
  listCities: City[] = [];

  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit(): void {
    this.getHotelDetails();
    this.getCities();
    }

    getHotelDetails(): void {
      const urlSegments = this.router.url.split('/');
      const id = urlSegments[urlSegments.length - 1];
      console.log('ID récupéré :', id);
  
      if (id) {
          this.apiService.getHotelById(+id).subscribe({
              next: (hotel) => {
                  this.hotel = hotel;
              },
              error: (err) => {
                  console.log('Erreur lors de la récupération des détails', err);
              }
          });
      }
  }
  
  getCityNameById(cityId: number | null): string {
    
    const city = this.listCities.find((c: City) => c.id === cityId);

    return city ? city.name : 'Ville inconnue';
}

getCities(): void {
  this.apiService.getCities().subscribe({
      next: (cities) => {
          this.listCities = cities;
         
          // Une fois que les villes sont chargées, on récupère les détails de l'hôtel
          this.getHotelDetails();
      },
      error: (err) => {
          console.error('Erreur lors de la récupération des villes', err);
      }
  });
}

//Retour à la liste des hôtels
goBack():void{
  this.router.navigateByUrl('/hotels');
}

}
