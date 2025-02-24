import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-details-hotel',
  templateUrl: './details-hotel.component.html',
  styleUrls: ['./details-hotel.component.css']
})
//export class DetailsHotelComponent implements OnInit {
  //hotel: Hotel = {
   // id: 0,
   // name: '',
   // phone: '',
   // address: '',
   // stars: 0,
   // rooms: 0,
   // price: 0,
   // imageUrl: ''
 // };

 // constructor(private router: Router, private apiService: ApiService) {}

 // ngOnInit(): void {}

 // onSubmit(): void {
    //this.apiService.addHotel(this.hotel).subscribe({
     // next: () => {
       // console.log('Hôtel ajouté avec succès');
       // this.router.navigate(['/']); // Redirige vers la liste des hôtels après l'ajout
      //},
      //error: (err) => {
       // console.log('Erreur lors de l\'ajout de l\'hôtel', err);
     // }
   // });
 // }


  //goBack(): void {
   // this.router.navigate(['/']);
 // }

//}

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
