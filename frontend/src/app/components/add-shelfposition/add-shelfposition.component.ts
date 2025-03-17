import { Component, inject, Inject, OnDestroy } from '@angular/core';
import { ShelfPosition } from '../../model/shelfPosition/shelf-position';
import { FormsModule } from '@angular/forms';
import { ShelfPositionService } from '../../services/shelfPositionService/shelf-position-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-add-shelfposition',
  imports: [FormsModule],
  templateUrl: './add-shelfposition.component.html',
  styleUrl: './add-shelfposition.component.css'
})
export class AddShelfpositionComponent implements OnDestroy{
  private subscribtion : Subscription | undefined;
  shelfPositionService = inject(ShelfPositionService);
  toast = inject(ToastrService);
  router = inject(Router)

  shelfPosition: ShelfPosition = {
    name: undefined,
  }

  saveShelfPosition(){
    try {
      if(this.shelfPosition.name == undefined){
        this.toast.error("Please add Shelf Position Details");
        return;
      }
      this.subscribtion = this.shelfPositionService.saveShelfPosition(this.shelfPosition).subscribe({
        next: (res: ShelfPosition)=>{
          console.log(this.shelfPosition);
          this.shelfPosition.name = "";
          this.toast.success("Shelf Position added successfully")
          this.router.navigate(['/shelfPositions'])
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
        }
      })
    } catch (error) {
      console.log(error)
    }
  }

  ngOnDestroy(): void {
    if(this.subscribtion){
      this.subscribtion.unsubscribe();
    }
  }
}