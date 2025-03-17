import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { ShelfSummaryData } from '../../model/shelfSummaryData/shelf-summary-data';
import { ToastrService } from 'ngx-toastr';
import { ShelfService } from '../../services/shelfService/shelf-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-shelf-summary',
  imports: [MatIconModule],
  templateUrl: './shelf-summary.component.html',
  styleUrl: './shelf-summary.component.css'
})
export class ShelfSummaryComponent implements OnInit,OnDestroy {
  private subscription: Subscription | undefined;
  toast = inject(ToastrService);
  shelfService = inject(ShelfService)
  router = inject(ActivatedRoute);
  id: number|undefined = undefined;
  shelfData: ShelfSummaryData = {
    shelf: undefined,
    shelfPositions: []
  };

  getShelfSummary(id:number){
    try {
      this.subscription = this.shelfService.getShelfSummary(id).subscribe({
        next:(res:any)=>{
          if(res != null || res != undefined){
            this.shelfData = res;
            console.log(this.shelfData);
            console.log(this.shelfData.shelfPositions);
            
          }
        },
        error:(err:HttpErrorResponse)=>{
          console.log(err);
          this.toast.error("unable to fetch")
        }
      })
    } catch (error) {
    }
  } 
  ngOnInit(): void {
    const idParam = this.router.snapshot.paramMap.get('id');
    this.id = Number(idParam);
    this.getShelfSummary(this.id);
  }

  ngOnDestroy(): void {
    if(this.subscription){
      this.subscription.unsubscribe();
    } 
  }
}
