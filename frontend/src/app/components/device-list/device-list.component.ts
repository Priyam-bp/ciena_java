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
import {
  MatDialog,
} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { EditComponentComponent } from '../edit-component/edit-component.component';
import { DeletemodalComponent } from '../deletemodal/deletemodal.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-device-list',
  standalone: true,
  imports: [CommonModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule],
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.css'] 
})
export class DeviceListComponent implements OnInit {

  deviceService = inject(DeviceService); 
  shelfPositionService = inject(ShelfPositionService);
  toast = inject(ToastrService);
  
  deviceItems = signal<Array<Device>>([]);
  deviceId: number | null =null;
  availableShelfPositions = signal<Array<ShelfPosition>>([]);
  selectedShelfPosition: number | null= null;

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
      this.deviceService.getAllDevices()
      .pipe(
        catchError((err) => {
          console.log("Error fetching devices:", err);
          return of([]); 
        })
      )
      .subscribe((devices) => {
        this.deviceItems.set(devices);
        console.log(devices);
        
      });
    } catch (error) {
      console.log(error);
    }
  }
  ngOnInit(): void {
    this.callDevice();
  }  

  onOpen(id: number){
    console.log("Selected Device"+id);
    this.deviceId= id;

    this.getAvailableShelfPositions();
    
    if(typeof document !== "undefined"){
      const modal = document.getElementById('exampleModalLong');
      if(modal){
        const mod = new bootstap.Modal(modal);
        mod.show()
      }
    }
  }

  onClose(){
    if(typeof document !== "undefined"){
      const modal = document.getElementById('exampleModalLong');
      if(modal){
        const mod = bootstap.Modal.getInstance(modal);
        mod?.hide()
      }
      this.selectedShelfPosition = null;
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
    if(this.selectedShelfPosition === null){
      this.selectedShelfPosition = id;
    }
  }

  addShelfPositionToDevice(){
    try {
      console.log(this.selectedShelfPosition, this.deviceId);
      
      if(this.selectedShelfPosition == null || this.deviceId == null){
        alert('Please select a Shelf & a Shelf Position')
        return;
      }
      
      const data:Addshelfpositionrequest = {
        deviceId: this.deviceId,
        shelfPositionId: this.selectedShelfPosition
      }

      this.deviceService.addShelfPositionToDevice(data).subscribe({
        next: (res: any)=>{
          console.log(res);
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
        }
      })
    } catch (error) {
      console.log(error)
    }
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
          console.log(err);
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
          console.log(err);
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
}
