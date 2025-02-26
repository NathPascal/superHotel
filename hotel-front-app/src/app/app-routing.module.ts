import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HotelsComponent } from './components/hotels/hotels.component';
import { DetailsHotelComponent } from './components/details-hotel/details-hotel.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { CitiesComponent } from './components/cities/cities.component';
import { CityFormComponent } from './components/city-form/city-form.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AuthGuard } from './guards/auth.guard';
import { SearchBarComponent } from './components/search-bar/search-bar.component';



const routes: Routes = [
  {path: '', redirectTo: '/hotels', pathMatch: 'full' },
  {path : 'hotels', component : HotelsComponent},
  {path : 'details-hotel/:id', component: DetailsHotelComponent},
  {path : 'add-hotel', component: HotelComponent, canActivate: [AuthGuard]},
  {path : 'update-hotel/:id', component: HotelComponent, canActivate: [AuthGuard]},
  {path: 'cities', component: CitiesComponent, canActivate: [AuthGuard]},
  {path: 'add-city', component: CityFormComponent, canActivate: [AuthGuard]},
  {path: 'update-city/:id', component: CityFormComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
   
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
