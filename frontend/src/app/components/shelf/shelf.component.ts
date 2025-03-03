import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { ShelfService } from '../../services/shelfService/shelf-service.service';
import { Shelf } from '../../model/shelf/shelf';
import { catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { EditComponentComponent } from '../edit-component/edit-component.component';
import { HttpErrorResponse } from '@angular/common/http';
import { DeletemodalComponent } from '../deletemodal/deletemodal.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-shelf',
  imports: [MatFormFieldModule, MatInputModule],
  templateUrl: './shelf.component.html',
  styleUrl: './shelf.component.css'
})
export class ShelfComponent implements OnInit{
  shelfService = inject(ShelfService);
  shelfItems = signal<Array<Shelf>>([]);
  toast = inject(ToastrService);

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

  //delete 
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
          this.toast.success("Shelf Deleted")
          this.callShelf();
          this.delDialog.closeAll()
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
          this.toast.error("Unable to delete")
        }
      })
    } catch (error) {
      console.log(error);
      this.toast.error('unable to delete')
    }
  }
  
  //Edit
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
          this.toast.success("Shelf Updated Succesfully");
          this.dialog.closeAll();
          this.callShelf();
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
          this.toast.error("Toast Deleted")
        }
      })
    } catch (error) {
      console.log(error);
      this.toast.error("unable to edit")
    }
  }

  //filter
  filterText = signal('');

  filteredShelves = computed(()=>{
    const filter = this.filterText().trim().toLowerCase();
    if(!filter){
      return this.shelfItems();
    }
    return this.shelfItems().filter(shelf => shelf.name?.toLowerCase().includes(filter));
  })

  applyFilter(event: Event){
    const filter = event.target as HTMLInputElement;
    this.filterText.set(filter.value);
  }
}