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
import { MatDialog } from '@angular/material/dialog';
import { EditComponentComponent } from '../edit-component/edit-component.component';
import { DeletemodalComponent } from '../deletemodal/deletemodal.component';

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
  editObj: any ={
    id: undefined,
    name:'',
  }
  delObj: any ={
    id: undefined,
    name:'',
  }
  constructor(private dialog: MatDialog){}

  openDialog(id: number,name: string): void{
    this.editObj= {id ,name};
    this.dialog.open(EditComponentComponent,{
      data:{title: "Shelf Position", firstInput: "Edit Shelf Position Name",editObj : this.editObj,submit: this.submit.bind(this)}
    })
  }

  submit = (id: number, shelfPosition: ShelfPosition)=>{
    console.log("submit called");
    this.shelfPositionService.editShelfPosition(id,shelfPosition).subscribe({
      next: (res: any)=>{
        console.log(res);
      },
      error: (err: HttpErrorResponse)=>{
        console.log(err);
      }
    })
  }
  

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

  readonly delDialog = inject(MatDialog)

  openDelDialog(id:number,name: string){
    this.delObj = {id,name};

    this.delDialog.open(DeletemodalComponent,{
      data:{title:"Shelf Position",delObj: this.delObj,submit: this.delSubmit.bind(this)}
    })
  }

  delSubmit=(id:number)=>{
    console.log(id);

    this.shelfPositionService.deleteShelf(id).subscribe({
      next: (res: any)=>{
        console.log(res);
      },
      error: (err: HttpErrorResponse)=>{
        console.log(err);
      }
    })
  }
}
