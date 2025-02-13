import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Shelf } from '../../model/shelf/shelf';
import { ShelfService } from '../../services/shelfService/shelf-service.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-shelf',
  imports: [FormsModule],
  templateUrl: './add-shelf.component.html',
  styleUrl: './add-shelf.component.css'
})
export class AddShelfComponent {

  shelfService = inject(ShelfService);

  shelf: Shelf={
    name:'',
    shelfType:'',
    shelfPositionId:0,
  }

  saveShelf(): void{
    this.shelfService.saveShelf(this.shelf).subscribe({
      next: (res:Shelf)=>{
        console.log(res);
      },
      error: (err:HttpErrorResponse)=>{
        console.log(err);
      }
    })
  }
}
