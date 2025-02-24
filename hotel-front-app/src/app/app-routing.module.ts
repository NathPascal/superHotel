import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HotelsComponent } from './components/hotels/hotels.component';
import { InfosHotelComponent } from './components/infos-hotel/infos-hotel.component';

const routes: Routes = [
  {path : 'hotels', component : HotelsComponent},
  { path: 'hotel/:id', component: InfosHotelComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
