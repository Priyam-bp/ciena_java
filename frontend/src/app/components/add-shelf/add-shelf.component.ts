import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Shelf } from '../../model/shelf/shelf';
import { ShelfService } from '../../services/shelfService/shelf-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import {ToastrModule, ToastrService} from 'ngx-toastr'
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-shelf',
  imports: [FormsModule,ToastrModule],
  templateUrl: './add-shelf.component.html',
  styleUrl: './add-shelf.component.css',
  providers:[]
})
export class AddShelfComponent {

  shelfService = inject(ShelfService);
  toast = inject(ToastrService);
  router = inject(Router);
  shelfPositionCount!: number | null;

  shelf: Shelf={
    name:undefined,
    shelfType:undefined,
    shelfPositionId:0,
  }

  saveShelf(): void{
    try {
      if(this.shelf.name == undefined || this.shelf.shelfType == undefined || this.shelfPositionCount == null){
        this.toast.error("Please fill all shelf details");
        return
      } 
      this.shelfService.saveShelf(this.shelf.name,this.shelf.shelfType,this.shelfPositionCount).subscribe({
        next: (res:Shelf)=>{
          console.log(res);
          this.toast.success("Shelf Added Succesfully")
          this.router.navigate(['/shelf']);
        },
        error: (err:HttpErrorResponse)=>{
          console.log(err);
          this.toast.error("Unable to add this shelf")
        }
      })
    } catch (error) {
      console.log(error);
    }
  }
}
