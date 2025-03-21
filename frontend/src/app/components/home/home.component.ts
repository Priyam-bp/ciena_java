import { Component, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  cardItems = [
    {
      id: 1,
      name: "Device",
      subHeading:" Manage your device inventory",
      button: "+ Add Device",
      iconLink:"bi bi-laptop",
      color:"blue",
      path:'/adddevice'
    },
    {
      id:2 ,
      name:"Shelf",
      subHeading:"Organize your storage shelves",
      button:"+ Add Shelf",
      iconLink:'bi bi-box-seam',
      color:"green",
      path:'/addshelf'
    },
  ]
}
