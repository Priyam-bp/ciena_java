import { Component, inject, OnInit, signal } from '@angular/core';
import { ShelfPositionService } from '../../services/shelfPositionService/shelf-position-service.service';
import { ShelfPosition } from '../../model/shelfPosition/shelf-position';
import { catchError, of } from 'rxjs';
import { Shelf } from '../../model/shelf/shelf';
import * as bootstrap from 'bootstrap';
import { ShelfService } from '../../services/shelfService/shelf-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Addshelftoshelfposition } from '../../model/addShelfToShelfPosition/addshelftoshelfposition';

@Component({
  selector: 'app-shelf-position-list',
  imports: [CommonModule],
  standalone: true,
  templateUrl: './shelf-position-list.component.html',
  styleUrl: './shelf-position-list.component.css'
})
export class ShelfPositionListComponent implements OnInit {

  shelfPositionService = inject(ShelfPositionService);
  shelfPositionItems = signal<Array<ShelfPosition>>([]);
  selectedShelfPositionId: number|null = null;
  selectedShelfId: number| null = null;
  availableShelves = signal<Array<Shelf>>([]); 
  shelfService = inject(ShelfService);
  

  ngOnInit(): void {
      this.shelfPositionService.getAllShelfPositions()
        .pipe(
          catchError((err)=>{
            console.log(err);
            return of([]);
          })
        )
        .subscribe((shelfPositions)=>{
          this.shelfPositionItems.set(shelfPositions);
          
        })
  }
  onOpen(id: number){
    this.selectedShelfPositionId = id;
    console.log(id);
    
    this.getAvailableShelves();

    if(typeof document !== "undefined"){
        const modal = document.getElementById('exampleModalLong');
        if(modal){
          const mod = new bootstrap.Modal(modal);
          mod.show()
        }
    }
  }

  onClose(){
    if(typeof document !== "undefined"){
      const modal = document.getElementById('exampleModalLong');
      if(modal){
        const mod = bootstrap.Modal.getInstance(modal);
        mod?.hide()
      }
    }
  }

  getAvailableShelves(){
    this.shelfService.getAvailableShelves().subscribe({
      next: (res: Array<Shelf>)=>{
        this.availableShelves.set(res);
      },
      error: (err: HttpErrorResponse)=>{
        console.log(err);
      }
    })
  }

  selectedShelfIdUpdate(id: number){
    if(this.selectedShelfId === null){
      this.selectedShelfId = id;
    }
    else{
      this.selectedShelfId = null;
    }
  }

  addShelfToShelfPosition(){
    if(this.selectedShelfId == null || this.selectedShelfPositionId == null){
      alert('Please select both a Shelf Position and a Shelf');
      return;
    }

    const data: Addshelftoshelfposition ={
      shelfId : this.selectedShelfId,
      shelfPositionId: this.selectedShelfPositionId
    }

    console.log(data);
    
    this.shelfPositionService.addShelftoShelfPosition(data).subscribe({
      next: (res: any)=>{
        console.log(res,"Added");
      },
      error: (err: HttpErrorResponse)=>{
        console.log(err);
      }
    })
  }
}
