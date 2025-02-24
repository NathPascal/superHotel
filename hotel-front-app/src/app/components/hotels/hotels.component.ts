import { Component, OnInit } from '@angular/core';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {

  listHotels : Hotel[] | undefined;

  constructor(private apiService: ApiService){}

  ngOnInit(): void {
      this.getHotels();
  }

  getHotels(): void {
    this.apiService.getHotels().subscribe({
      next: (hotels) => {
        this.listHotels = hotels;
      },
      error: (err) => {
        console.log('Erreur lors de la récupération des formations', err);
      }
    });
  }

}
