import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent implements OnInit {

  listCities: City[] = [];

  constructor(private apiService: ApiService, private router: Router){}

  ngOnInit(): void {
      this.getCities();
  
  }

  getCities(): void {
    this.apiService.getCities().subscribe({
      next: (cities) => this.listCities = cities,
      error: (err: any) => console.error('Erreur lors de la récupération des cilles', err)
    });
  }
 
  onAddCity(): void{
    this.router.navigateByUrl('/add-city');
  }

  onUpdateCity(id:number): void{
    this.router.navigate(['update-city', id]);
  }

  onDeleteCity(id:number): void {
    if (confirm('Etes-vous sûr de vouloir supprimer cette ville ?')){
      this.apiService.deleteCity(id).subscribe({
        next: () => {
          alert('Ville supprimée avec succès');
          this.getCities();
        },
        error: (err : any) => console.error('Erreur lors de la suppression de la ville', err)
      });
    }
  }

  //Retour à la liste des hôtels
  goBack():void{
    this.router.navigateByUrl('/hotels');
  }
}
