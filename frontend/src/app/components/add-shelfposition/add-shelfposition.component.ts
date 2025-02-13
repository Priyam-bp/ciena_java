import { Component, inject, Inject } from '@angular/core';
import { ShelfPosition } from '../../model/shelfPosition/shelf-position';
import { FormsModule } from '@angular/forms';
import { ShelfPositionService } from '../../services/shelfPositionService/shelf-position-service.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-shelfposition',
  imports: [FormsModule],
  templateUrl: './add-shelfposition.component.html',
  styleUrl: './add-shelfposition.component.css'
})
export class AddShelfpositionComponent {
  shelfPositionService = inject(ShelfPositionService);

  shelfPosition: ShelfPosition = {
    name: '',
  }

  saveShelf(){
    this.shelfPositionService.saveShelfPosition(this.shelfPosition).subscribe({
      next: (res: ShelfPosition)=>{
        console.log(this.shelfPosition);
        this.shelfPosition.name = "";
      },
      error: (err: HttpErrorResponse)=>{
        console.log(err);
      }
    })
  }
}
