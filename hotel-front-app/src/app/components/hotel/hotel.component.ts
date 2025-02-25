import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent {

  hotel : Hotel = {
    id: 0,
    name: '',
    phone: '',
    address: '',
    stars: 0,
    rooms: 0,
    price: 0,
    imageUrl: ''
  };

  constructor(private apiService: ApiService, private router: Router){}

  onSubmit(): void {
    this.apiService.addHotel(this.hotel).subscribe({
      next: ()=>{
        console.log('Hotel ajouté avec succès');
        this.router.navigateByUrl('/hotels');
      },
      error: (err: any)=> {
        console.log('Erreur lors de l\ajout de l\'hôtel', err);
      }
    });
  }

  goBack():void{
    this.router.navigateByUrl('/hotels');
  }

}
