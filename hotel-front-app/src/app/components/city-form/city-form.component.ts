import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { City } from 'src/app/models/city.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-city-form',
  templateUrl: './city-form.component.html',
  styleUrls: ['./city-form.component.css']
})
export class CityFormComponent implements OnInit {

  city: City = { 
    id: 0,
    name: '',
    hotels: [] 
  };
  isUpdateMode: boolean = false;

  constructor(private apiService: ApiService, private router: Router, private route: ActivatedRoute){}

  ngOnInit(): void {
      const id = this.route.snapshot.paramMap.get('id');
      if (id) {
        this.isUpdateMode = true;
        this.apiService.getCityById(+id).subscribe({
          next: (city) => this.city = city,
          error: (err: any) => console.error('Erreur lors de la récupération de la ville', err)
        });
      }
  }

  onSubmit(): void {
    if (this.isUpdateMode) {
      this.apiService.updateCity(this.city.id, this.city).subscribe({
        next: () => {
          alert('Ville mise à jour avec succès');
          this.router.navigateByUrl('/cities');
        },
        error: (err: any) => console.error('Erreur lors de l\'ajout de la ville', err)
      });
    }else {
      //ajout d'une nouvelle ville
    this.apiService.addCity(this.city).subscribe({
      next: ()=>{
        console.log('Ville ajoutée avec succès');
        this.router.navigateByUrl('/cities');
      },
      error: (err: any)=> {
        console.log('Erreur lors de l\ajout de la ville', err);
      }
    });
  }
  }

  goBack(): void {
    this.router.navigateByUrl('cities');
  }

}
