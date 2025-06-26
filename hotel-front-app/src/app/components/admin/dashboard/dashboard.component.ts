import { Component } from '@angular/core';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  hotels: Hotel[] = []; // Tableau pour stocker les hôtels
  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    //Récup tous les hôtels
    this.apiService.getHotels().subscribe(
      (data) => {
        this.hotels = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des hôtels', error);
      }
    );

    // Pour chaque hôtel, récup commentaire
    this.hotels.forEach((hotel) => {
      this.apiService.getCommentById(hotel.id).subscribe(
        (comment) => {
          hotel.comment = comment; // Ajoute le commentaire à l'hôtel
        },
        (error) => {
          console.error(`Erreur lors de la récupération du commentaire pour l'hôtel ${hotel.id}`, error);
        }
      );
    });
  }
}
