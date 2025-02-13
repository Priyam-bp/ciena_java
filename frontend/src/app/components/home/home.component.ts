import { Component, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  cardItems = signal([
    {
      id: 1,
      name: "Device",
      subHeading:" Manage your device inventory",
      button: "+ Add Device",
      iconLink:"bi bi-laptop",
      color:"blue",
      path:'devices/adddevice'
    },
    {
      id:2 ,
      name:"Shelf",
      subHeading:"Organize your storage shelves",
      button:"+ Add Shelf",
      iconLink:'bi bi-box-seam',
      color:"green",
      path:'shelf/addshelf'
    },
    {
      id: 3,
      name:"Shelf Position",
      subHeading:"Manage your shelf positions",
      button:"+ Add Position",
      iconLink:'bi bi-grid',
      color:"purple",
      path:'shelfposition/addshelfposition'
    }
  ])
}
