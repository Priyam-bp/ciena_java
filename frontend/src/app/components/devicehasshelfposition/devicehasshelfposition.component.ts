import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, inject, signal } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogContent } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatStepperModule } from '@angular/material/stepper';
import { ToastrService } from 'ngx-toastr';
import { DeviceService } from '../../services/deviceService/device.service';
import { Addshelfpositionrequest } from '../../model/addShelfPositionRequest/addshelfpositionrequest';

@Component({
  selector: 'app-devicehasshelfposition',
  imports: [CommonModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule,MatStepperModule,ReactiveFormsModule,MatFormFieldModule,MatDialogContent],
  templateUrl: './devicehasshelfposition.component.html',
  styleUrl: './devicehasshelfposition.component.css'
})
export class DevicehasshelfpositionComponent {
  toast = inject(ToastrService);
  deviceService = inject(DeviceService);
  selectedShelfId: number | null = null;
  shelfPositionId: number | null = null;
  shelfPositionName: string | null = null;
  constructor(@Inject(MAT_DIALOG_DATA) public data :any){
      console.log(data);
  }

  selectShelf(id:number){
    this.selectedShelfId = this.selectedShelfId === id ? null : id;
  }

  selectedShelfPositionId(id:number,shelfPositionName:string){
    this.shelfPositionName = this.shelfPositionName == shelfPositionName ? null : shelfPositionName;
    this.shelfPositionId = this.shelfPositionId == id ? null : id;
  }

  addShelfPositionToDevice(){
      try {
        console.log(this.shelfPositionId, this.data.deviceId);
        
        if(this.shelfPositionId == null || this.data.deviceId == null){
          this.toast.error('Please select a Shelf & a Shelf Position')
          return;
        }
        
        const data:Addshelfpositionrequest = {
          deviceId: this.data.deviceId,
          shelfPositionId: this.shelfPositionId
        }
  
        this.deviceService.addShelfPositionToDevice(data).subscribe({
          next: (res: any)=>{
            console.log(res);
            this.toast.success("Relationship added")
          },
          error: (err: HttpErrorResponse)=>{
            this.toast.error(err.error.message)
          }
        })
      } catch (error) {
        console.log(error)
      }
    }

  private _formBuilder = inject(FormBuilder);

  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
}
