import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfosHotelComponent } from './infos-hotel.component';

describe('InfosHotelComponent', () => {
  let component: InfosHotelComponent;
  let fixture: ComponentFixture<InfosHotelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InfosHotelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfosHotelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
