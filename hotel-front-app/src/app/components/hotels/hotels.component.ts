import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {

  listHotels : Hotel[] = [];

  constructor(private apiService: ApiService, private router: Router){}

  ngOnInit(): void {
      this.getHotels();
  }

  //Récupère tous les hôtels depuis l'API
  getHotels(): void {
    this.apiService.getHotels().subscribe({
      next: (hotels) => {
        this.listHotels = hotels;
      },
      error: (err) => {
        console.log('Erreur lors de la récupération des hôtels', err);
      }
    });
  }

  //Redirige vers la page d'ajout d'un hôtel
  onAddHotel(): void {
    this.router.navigateByUrl('/add-hotel');
  }

  //Redirige vers la page de modification d'un hôtel
  onUpdateHotel(id:number):void{
    this.router.navigate(['update-hotel', id]);
  }

  //Supprime un hôtel via l'API
  onDeleteHotel(id:number): void {
    if (confirm('Etes-vous sûr de vouloir supprimer cet hôtel?')){
      this.apiService.deleteHotel(id).subscribe({
        next: () => {
          alert('Hôtel supprimé avec succès');
          this.getHotels();
        },
        error: (err: any) => {
          console.error('Erreur lors de la suppression de l\'hôtel', err);
        }
      });
    }
  }

}
