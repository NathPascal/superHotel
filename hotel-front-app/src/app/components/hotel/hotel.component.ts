import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {

  hotel : Hotel = {
    id: 0,
    name: '',
    phone: '',
    address: '',
    stars: 0,
    rooms: 0,
    price: 0,
    imageUrl: '',
    cityId: null
  };

  listCities : City[] = [];
  isUpdateMode: boolean = false; 

  constructor(private apiService: ApiService, private router: Router, private route: ActivatedRoute){}

  ngOnInit(): void { 
    this.getCities();

    // Vérifie si un ID est présent dans l'URL pour basculer en mode update
    const hotelID = this.route.snapshot.paramMap.get('id');
    if (hotelID){
      this.isUpdateMode = true;
      this.getHotelById(+hotelID);
    }
  }

  getCities(): void {
    this.apiService.getCities().subscribe({
      next: (cities) => {
        this.listCities = cities;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des villes', err);
      }
    });
  }

  //Récupère les données de l'hôtel en mode update
  getHotelById(id:number): void {
    this.apiService.getHotelById(id).subscribe({
      next: (hotel) => {
        this.hotel = hotel;
      },
      error: (err : any) => {
        console.error('erreur lors de la récupération de l\'hôtel', err);
      }
    });
  }

  //Soumission du formulaire pour ajout ou mise à jour
  onSubmit(): void {
    if (this.isUpdateMode){
      //Mise à jour d'un hôtel existant
      this.apiService.updateHotel(this.hotel.id, this.hotel).subscribe({
        next: ()=> {
          console.log('Hôtel mis à jour avec succès');
          this.router.navigateByUrl('/hotels');
        },
        error: (err: any) => {
          console.log('Erreur lors de la mise à jour de l\'hôtel', err);
        }
      });
    }else {
      //ajout d'un nouvel hôtel
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
}

  //Suppression de l'hôtel
  onDeleteHotel(): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cet hôtel ?')) {
      this.apiService.deleteHotel(this.hotel.id).subscribe({
        next: () => {
          console.log('Hôtel supprimé avec succès');
          this.router.navigateByUrl('/hotels');
        },
        error: (err) => {
          console.error('Erreur lors de la suppression de l\'hôtel', err);
        }
      });
    }
  }

  //Retour à la liste des hôtels
  goBack():void{
    this.router.navigateByUrl('/hotels');
  }

}
