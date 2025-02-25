import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HotelsComponent } from './components/hotels/hotels.component';
import { DetailsHotelComponent } from './components/details-hotel/details-hotel.component';
import { HotelComponent } from './components/hotel/hotel.component';



const routes: Routes = [
  { path: '', redirectTo: '/hotels', pathMatch: 'full' },
  {path : 'hotels', component : HotelsComponent},
  {path : 'details-hotel/:id', component: DetailsHotelComponent},
  {path : 'add-hotel', component: HotelComponent},
  {path : 'update-hotel/:id', component: HotelComponent},
  

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
