import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { Hotel } from 'src/app/models/hotel.model';
import { ApiService } from 'src/app/services/api.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {

  listHotels : Hotel[] = [];
  listCities: City[] = [];
  selectedCityId: number | null = null;
  error = null; 
  isLoggedIn: boolean = false;

 // @Input() Cities: City[] | undefined = [];
 // @Output() newItemEvent = new EventEmitter<number>();


  constructor(private apiService: ApiService, private router: Router, private authService: AuthService){}

  ngOnInit(): void {
      this.getHotels();
      this.getCities();
      this.isLoggedIn = this.authService.isLoggedIn;

  }

  filterCities(id: number) {
    this.selectedCityId = id;
    this.getHotelsByCity(id);
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

  //Récupère toutes les villes depuis l'API
  getCities(): void {
    this.apiService.getCities().subscribe({
      next: (cities) => {
        this.listCities = cities;
      },
      error: (err) => {
        console.log('Erreur lors de la récupération des villes', err);
      }
    });
  }

  // fonction pour obtenir le nom de la ville par son Id
  getCityNameById(cityId: number | null): string {
    const city = this.listCities.find(c => c.id === cityId);
    console.log('Ville trouvée pour ID', cityId, ':', city?.name);
    return city ? city.name : 'Ville inconnue';
}

getHotelsByCity(cityId: number) {
  this.apiService.getHotelsByCity(cityId).subscribe({
    next: (hotels) => {
      this.listHotels = hotels;
    },
    error: (err) => {
      console.log('Erreur lors de la récupération des hôtels pour la ville', err);
    }
   
  });
}


  //Filtre les hôtels par ville
  //filterByCity(cityId: number | null): void {

   // if (cityId) {
     // this.apiService.getHotelsByCity(cityId).subscribe({
      //  next: (hotels: Hotel[]) =>{
       //   this.listHotels = hotels;

      //console.log('Hôtels filtrés par ville:', this.listHotels); //log
      //  },
       // error: (err: any) => {
        //  console.log('Erreur lors du filtrage des hôtels par ville', err);
       // }     
     // });
   // }else {
    //  this.getHotels(); 
   // }
  //}

 // onCityFilterChange(event: Event): void {
   // const selectElement = event.target as HTMLSelectElement;
   // const cityId = selectElement.value? +selectElement.value : null;

   // console.log('Changement de ville sélectionnée: ', cityId);//log

   // this.filterByCity(cityId);
 // }



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

  // Redirige vers la page des villes
  goToDashboard(): void {
    this.router.navigateByUrl('/cities');
  }

}
