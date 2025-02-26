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

  constructor(private router: Router, private apiService: ApiService) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.getHotelDetails();
      }
    });
  }

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
                  console.log('Données de l\'hôtel :', hotel);
                  this.hotel = hotel;
  
                  // Afficher l'ID de la ville depuis cityId
                  console.log('ID de la ville associé à l\'hôtel :', hotel.cityId ?? 'ID non défini');
              },
              error: (err) => {
                  console.log('Erreur lors de la récupération des détails', err);
              }
          });
      }
  }
  
  getCityNameById(cityId: number | null): string {
    console.log('Recherche du nom de la ville pour ID :', cityId);
    console.log('Liste des villes disponibles :', this.listCities.map(city => `${city.id} - ${city.name}`));

    const city = this.listCities.find((c: City) => c.id === cityId);

    console.log('Ville trouvée :', city);
    return city ? city.name : 'Ville inconnue';
}

getCities(): void {
  this.apiService.getCities().subscribe({
      next: (cities) => {
          this.listCities = cities;
          console.log('Liste des villes chargée :', this.listCities);

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
