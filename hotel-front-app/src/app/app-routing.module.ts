import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HotelsComponent } from './components/hotels/hotels.component';
import { DetailsHotelComponent } from './components/details-hotel/details-hotel.component';


const routes: Routes = [
  {path : 'hotels', component : HotelsComponent},
  {path : 'details-hotel/:id', component: DetailsHotelComponent},

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
