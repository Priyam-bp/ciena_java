import { Component, inject, OnInit } from '@angular/core';
import { Device } from '../../model/device/device';
import {FormControl, FormsModule, NgForm} from '@angular/forms'
import { DeviceService } from '../../services/deviceService/device.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-add-device',
  imports: [FormsModule],
  templateUrl: './add-device.component.html',
  styleUrl: './add-device.component.css'
})
export class AddDeviceComponent implements OnInit{

  deviceService = inject(DeviceService)

  device: Device ={
    id: 0,
    name:'',
    deviceType:''
  }

  ngOnInit(): void {
      
  }
  saveDevie(deviceForm :NgForm): void{
    this.deviceService.saveDevice(this.device).subscribe({
      next: (res: Device)=>{
        console.log(res);
        // deviceForm.reset();
      },
      error: (err: HttpErrorResponse)=>{
        console.log(err);
      }
    })
  }
}
