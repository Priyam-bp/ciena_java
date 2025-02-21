import { Component, computed, inject, OnInit, signal } from '@angular/core';
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
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Toast, ToastrService } from 'ngx-toastr';
import { MatButton } from '@angular/material/button';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-shelf-position-list',
  imports: [CommonModule,MatFormFieldModule, MatInputModule,MatButton,RouterLink],
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
  toast = inject(ToastrService);
  router = inject(Router)
  editObj: any ={
    id: undefined,
    name:'',
  }
  delObj: any ={
    id: undefined,
    name:'',
  }


  ngOnInit(): void {
    this.callShelfPositions()
  }

  callShelfPositions(){
    try {
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
    } catch (error) {
      this.toast.error("Unable to fetch Shelf Positions")
    }
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
    try {
      this.shelfService.getAvailableShelves().subscribe({
        next: (res: Array<Shelf>)=>{
          this.availableShelves.set(res);
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
        }
      })
    } catch (error) {
      this.toast.error("Unable to fetch shelves")
    }
  }

  selectedShelfIdUpdate(id: number){
    this.selectedShelfId = this.selectedShelfId === id ? null : id;
  }

  addShelfToShelfPosition(){
    try {
      if(this.selectedShelfId == null || this.selectedShelfPositionId == null){
        this.toast.error('Please select both a Shelf Position and a Shelf');
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
          this.toast.success("Relationship added");
          this.callShelfPositions();
          this.onClose();
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
          this.toast.error(err.error.message)
        }
      })
    } catch (error) {
      this.toast.error("Unable to establish relationship")
    }
  }
  
  //edit
  constructor(private dialog: MatDialog){}

  openDialog(id: number,name: string): void{
    this.editObj= {id ,name};
    this.dialog.open(EditComponentComponent,{
      data:{title: "Shelf Position", firstInput: "Edit Shelf Position Name",editObj : this.editObj,submit: this.submit.bind(this)}
    })
  }

  submit = (id: number, shelfPosition: ShelfPosition)=>{
    try {
      console.log("submit called");
      this.shelfPositionService.editShelfPosition(id,shelfPosition).subscribe({
        next: (res: any)=>{
          console.log(res);
          this.toast.success("Shelf Position Updated")
          this.callShelfPositions();
          this.dialog.closeAll();
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
          this.toast.error(err.error.message)
        }
      })
    } catch (error) {
      this.toast.error("Unable to edit, try again")
    }
  }


  //Delete Modal
  readonly delDialog = inject(MatDialog)

  openDelDialog(id:number,name: string){
    this.delObj = {id,name};

    this.delDialog.open(DeletemodalComponent,{
      data:{title:"Shelf Position",delObj: this.delObj,submit: this.delSubmit.bind(this)}
    })
  }

  delSubmit=(id:number)=>{
    try {
      console.log(id);

      this.shelfPositionService.deleteShelf(id).subscribe({
        next: (res: any)=>{
          console.log(res);
          this.toast.success("Deleted Succesfully")
          this.callShelfPositions();
          this.delDialog.closeAll()
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
        }
      })
    } catch (error) {
      this.toast.error("Unable to delete")
    }
  }

  // Filter Logic

  filterText = signal('');

  filteredShelfPositions = computed(()=>{
    const filter = this.filterText().trim().toLowerCase();
    
    if(!filter){
      return this.shelfPositionItems();
    }

    return this.shelfPositionItems().filter(shelfPosition => shelfPosition.name?.toLowerCase().includes(filter))
  })

  applyFilter(event: Event){
    const input = event.target as HTMLInputElement;
    this.filterText.set(input.value)
  }

  toAddShelf(){
    this.onClose()
    this.router.navigate(['/addshelf'])
  }
}
