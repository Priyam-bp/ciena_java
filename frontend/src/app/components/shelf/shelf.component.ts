import { Component, inject, OnInit, signal } from '@angular/core';
import { ShelfService } from '../../services/shelfService/shelf-service.service';
import { Shelf } from '../../model/shelf/shelf';
import { catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { EditComponentComponent } from '../edit-component/edit-component.component';
import { HttpErrorResponse } from '@angular/common/http';
import { DeletemodalComponent } from '../deletemodal/deletemodal.component';

@Component({
  selector: 'app-shelf',
  imports: [],
  templateUrl: './shelf.component.html',
  styleUrl: './shelf.component.css'
})
export class ShelfComponent implements OnInit{
  shelfService = inject(ShelfService);
  shelfItems = signal<Array<Shelf>>([]);
  editObj: Shelf = {
    id: undefined,
    name:undefined,
    shelfType:undefined
  }
  delObj: Shelf = {
    id: undefined,
    name:undefined,
    shelfType:undefined
  }


  constructor(private dialog: MatDialog){}

  openDialog(id:number,name: string,shelfType: string): void{
    this.editObj = {id,name,shelfType};

    this.dialog.open(EditComponentComponent,{
      data: {title:"Shelf",firstInput:"Shelf Name",secondInput:"Shelf Type",editObj: this.editObj, submit: this.submit.bind(this)}
    })
  }

  submit = (id: number,data: Shelf)=>{
    try {
      this.shelfService.updateShelf(id,data).subscribe({
        next: (res: any)=>{
          console.log(res);
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
        }
      })
    } catch (error) {
      console.log(error);
       
    }
  }

  readonly delDialog = inject(MatDialog)

  delOpenDialog(id:number,name:string,shelfType:string){
    this.delObj = {id,name,shelfType};

    this.delDialog.open(DeletemodalComponent,{
      data:{title:"Device",delObj: this.delObj,submit: this.delSubmit.bind(this)}
    })
  }

  delSubmit=(id:number)=>{
    try {
      this.shelfService.deleteShelf(id).subscribe({
        next: (res: any)=>{
          console.log(res);
          this.delDialog.closeAll()
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
        }
      })
    } catch (error) {
      console.log(error);
    }
  }
  ngOnInit(): void {
      this.callShelf();
  }
  callShelf(){
    try {
      this.shelfService.getAllShelves()
      .pipe(catchError((err)=>{
        console.log(err);
        return of([]);
      }))
      .subscribe((shelf)=>{
        this.shelfItems.set(shelf);
      })
    } catch (error) {
      console.log(error);
    }
  }
}
