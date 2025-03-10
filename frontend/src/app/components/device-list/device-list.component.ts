import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import * as bootstap from 'bootstrap';
import { Device } from '../../model/device/device';
import { catchError, of } from 'rxjs';
import { DeviceService } from '../../services/deviceService/device.service';
import { ShelfPosition } from '../../model/shelfPosition/shelf-position';
import { ShelfPositionService } from '../../services/shelfPositionService/shelf-position-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Addshelfpositionrequest } from '../../model/addShelfPositionRequest/addshelfpositionrequest';
import {MatButtonModule} from '@angular/material/button';
import {MatStepperModule} from '@angular/material/stepper';
import {
  MatDialog,
} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { EditComponentComponent } from '../edit-component/edit-component.component';
import { DeletemodalComponent } from '../deletemodal/deletemodal.component';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { DevicehasshelfpositionComponent } from '../devicehasshelfposition/devicehasshelfposition.component';
import { Shelf } from '../../model/shelf/shelf';

@Component({
  selector: 'app-device-list',
  standalone: true,
  imports: [CommonModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule,MatStepperModule,ReactiveFormsModule,MatFormFieldModule],
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.css'] ,
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: {showError: true},
    },
  ],
})
export class DeviceListComponent implements OnInit {

  deviceService = inject(DeviceService); 
  shelfPositionService = inject(ShelfPositionService);
  toast = inject(ToastrService);
  
  deviceItems = signal<Array<Device>>([]);
  deviceId: number | null =null;
  deviceName: string | null = null;
  availableShelfPositions = signal<Array<ShelfPosition>>([]);
  selectedShelfPosition: number | null= null;
  router = inject(Router);
  shelves = [];
  editObj: Device ={
    id: undefined,
    name:undefined,
    deviceType:undefined,
  }
  delObj: Device = {
    id: undefined,
    name:undefined,
    deviceType:undefined
  }
  
  callDevice(){
    try {
      this.deviceService.getAllDevices().subscribe({
        next: (devices: Array<Device>)=>{
          this.deviceItems.set(devices);
        },
        error: (err: HttpErrorResponse)=>{
          this.toast.error("Unable to fetch ")
        }
      })
    } catch (error) {
      console.log(error);
    }
  }
  ngOnInit(): void {
    this.callDevice();
    this.getShelves();
  }  
  addShelfPositionDialog = inject(MatDialog);

  onOpen(id:number,deviceName:string){
    this.deviceId = id;
    this.deviceName = deviceName;
    
    this.addShelfPositionDialog.open(DevicehasshelfpositionComponent,{
      data:{shelves: this.shelves,deviceId:this.deviceId,deviceName:deviceName}
    })
  }

  onClose(){
    
  }

  getShelves(){
    try {
      this.deviceService.getShelfAndShelfPositions().subscribe({
        next:(res: any)=>{
          this.shelves = res;
          console.log(res);
          
        }
      })
    } catch (error) {
      
    }
  }

  getAvailableShelfPositions(){
    try {
      this.shelfPositionService.getAllShelfPositions().subscribe({
        next: (res: Array<ShelfPosition>)=>{
          this.availableShelfPositions.set(res);
        },
        error : (err: HttpErrorResponse)=>{
          console.log(err);
        }
      })
    } catch (error) {
      console.log(error);
    }
  }

  selectedShelfPositionId(id:number){
    this.selectedShelfPosition = this.selectedShelfPosition === id ? null : id;
  }



  //Edit Decvice
  constructor(private dialog: MatDialog){}

  openDialog(id:number,nameOfDevice:string,deviceTypeOfDevice:string): void{
    this.editObj={id:id, name:nameOfDevice, deviceType:deviceTypeOfDevice,}
    console.log(this.editObj);
    
    this.dialog.open(EditComponentComponent,{
      width:'300px',
      data: {title: "Device", firstInput: "Edit Device Name", secondInput: "Device Type",editObj : this.editObj,submit: this.submitDevice.bind(this) }
    })
  }

  submitDevice = (id:number,deviceObj: Device) => {
    try {
      console.log("Submit called");
    
      this.deviceService.editDevice(id,deviceObj).subscribe({
        next: (res: any) => {
          console.log(res);
          this.toast.success("Device Updated")
          this.callDevice();
          this.dialog.closeAll()
        },
        error: (err: HttpErrorResponse) => {
          this.toast.error("Unable to edit, try again")
        }
      });
    } catch (error) {
      console.log(error);
      
    }
  };

  // Delete Device
  readonly delDialog = inject(MatDialog);

  openDelDialog(id:number,name:string,deviceType:string){
    this.delObj = {id,name,deviceType};
    
    this.delDialog.open(DeletemodalComponent,{
      data:{title:"Device",delObj: this.delObj,submit: this.delSubmit.bind(this)}
    })
    
  }

  delSubmit =(id: number)=>{
    try {
      if(id == undefined){
        this.toast.error("Undefined Device")
        return;
      }
      console.log(id);
    
      this.deviceService.deleteDevice(id).subscribe({
        next: (res: any)=>{
          console.log(res);
          this.toast.success("Device Deleted Succesfully")
          this.callDevice()
          this.delDialog.closeAll();
        },
        error: (err: HttpErrorResponse)=>{
          this.toast.error("Unable to delete, Try again")
        }
      })
    } catch (error) {
      console.log(error);
       
    }
  }

  filterText = signal('');

  filteredDevices = computed(()=>{
    const filter = this.filterText().trim().toLowerCase();
    if(!filter){
      return this.deviceItems();
    }
    return this.deviceItems().filter(device => device.name?.toLowerCase().includes(filter));
  })

  applyFilter(event: Event) {
    const input = event.target as HTMLInputElement;
    this.filterText.set(input.value)
  }

  toAddShelfPosition(){
    this.onClose();
    this.router.navigate(['/addshelfposition'])
  }
}
