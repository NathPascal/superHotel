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

  listHotels: Hotel[] = [];
  listCities: City[] = [];
  filteredHotels: Hotel[] = [];
  selectedCityId: number | null = null;
  isLoggedIn: boolean = false;

  constructor(
    private apiService: ApiService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getCities();
    this.updateHotelList();
    this.isLoggedIn = this.authService.isLoggedIn;

    this.filteredHotels = this.listHotels;
  }

  // Filtrer les hôtels par ville
  filterCities(id: number | string) {
    this.selectedCityId = id === 'all' ? null : (id as number);
    this.updateHotelList();
  }

  // Mise à jour de la liste des hôtels
  private updateHotelList() {
    if (this.selectedCityId !== null) {
      this.apiService.getHotelsByCity(this.selectedCityId).subscribe({
        next: (hotels) => {
          this.listHotels = hotels;
          this.filteredHotels = hotels;
        },
        error: (err) => console.error(err)
      });
    } else {
      this.apiService.getHotels().subscribe({
        next: (hotels) => {
          this.listHotels = hotels;
          this.filteredHotels = hotels;
        },
        error: (err) => console.error(err)
      });
    }
  }
  
  // Barre de recherche
  onSearch(query: string) {
    const searchTerm = query.toLowerCase();
    this.filteredHotels = this.listHotels.filter(hotel => 
      hotel.name.toLowerCase().includes(searchTerm) ||
      this.getCityNameById(hotel.cityId).toLowerCase().includes(searchTerm)
    );
  }

  // Récupération des villes
  getCities(): void {
    this.apiService.getCities().subscribe({
      next: (cities) => {
        this.listCities = cities;
      },
      error: (err) => console.error('Erreur lors de la récupération des villes', err)
    });
  }

  // Obtenir le nom de la ville par ID
  getCityNameById(cityId: number | null): string {
    return this.listCities.find(city => city.id === cityId)?.name ?? 'Ville inconnue';
  }

  // Redirections
  onAddHotel(): void {
    this.router.navigateByUrl('/add-hotel');
  }

  onUpdateHotel(id: number): void {
    this.router.navigate(['update-hotel', id]);
  }

  // Suppression d'un hôtel
  onDeleteHotel(id: number): void {
    if (confirm('Etes-vous sûr de vouloir supprimer cet hôtel ?')) {
      this.apiService.deleteHotel(id).subscribe({
        next: () => {
          alert('Hôtel supprimé avec succès');
          this.updateHotelList();
        },
        error: (err) => console.error('Erreur lors de la suppression de l\'hôtel', err)
      });
    }
  }

  goToDashboard(): void {
    this.router.navigateByUrl('/cities');
  }

}