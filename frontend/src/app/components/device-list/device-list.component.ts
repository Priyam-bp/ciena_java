import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common'; 


import { Device } from '../../model/device/device';
import { catchError, of } from 'rxjs';
import { DeviceService } from '../../services/deviceService/device.service';

@Component({
  selector: 'app-device-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './device-list.component.html',
  styleUrls: ['./device-list.component.css'] 
})
export class DeviceListComponent implements OnInit {

  deviceService = inject(DeviceService); 
  deviceItems = signal<Array<Device>>([]); // initialized array of devices with empty array which will be then sent to html as signal

  ngOnInit(): void {
    this.deviceService.getAllDevices()
      .pipe(
        catchError((err) => {
          console.log("Error fetching devices:", err);
          return of([]); 
        })
      )
      .subscribe((devices) => {
        this.deviceItems.set(devices);
      });
  }  
}
