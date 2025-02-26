import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HotelsComponent } from './components/hotels/hotels.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DetailsHotelComponent } from './components/details-hotel/details-hotel.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { CitiesComponent } from './components/cities/cities.component';
import { CityFormComponent } from './components/city-form/city-form.component';


@NgModule({
  declarations: [
    AppComponent,
    HotelsComponent,
    DetailsHotelComponent,
    HotelComponent,
    CitiesComponent,
    CityFormComponent
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
