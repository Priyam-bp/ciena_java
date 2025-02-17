import { Component, inject, OnInit } from '@angular/core';
import { Device } from '../../model/device/device';
import {FormControl, FormsModule, NgForm} from '@angular/forms'
import { DeviceService } from '../../services/deviceService/device.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-device',
  imports: [FormsModule],
  templateUrl: './add-device.component.html',
  styleUrl: './add-device.component.css'
})
export class AddDeviceComponent implements OnInit{

  deviceService = inject(DeviceService)
  toast = inject(ToastrService)
  router = inject(Router)

  device: Device ={
    name:undefined,
    deviceType:undefined
  }

  ngOnInit(): void {
      
  }
  saveDevice(): void{
    try {
      if(this.device.name == undefined || this.device.deviceType == undefined){
        this.toast.error("Please add all the device details");
        return
      }
      
      this.deviceService.saveDevice(this.device).subscribe({
        next: (res: Device)=>{
          console.log(res);
          this.toast.success("Device Added Successfully")
          this.router.navigate(['/devices'])
        },
        error: (err: HttpErrorResponse)=>{
          console.log(err);
          this.toast.error("Unable to add this device")
        }
      })
    } catch (error: any) {
      console.log(error);
      
    }
  }
}
