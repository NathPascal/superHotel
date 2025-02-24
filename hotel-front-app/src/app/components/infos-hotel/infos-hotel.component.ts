import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-infos-hotel',
  templateUrl: './infos-hotel.component.html',
  styleUrls: ['./infos-hotel.component.css']
})
export class InfosHotelComponent implements OnInit {
  hotel: Hotel | undefined;

  constructor(private route: ActivatedRoute, private apiService: ApiService){}

  ngOnInit(): void {
      this.getHotelDetails();
  }

  getHotelDetails(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if(id){
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

}
