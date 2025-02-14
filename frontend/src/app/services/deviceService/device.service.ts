import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Device } from '../../model/device/device';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  http = inject(HttpClient);

  url = `http://localhost:8080`;
  
  getAllDevices(){
    return this.http.get<Array<Device>>(`${this.url}/devices`);
  }

  saveDevice(device: Device): Observable<Device>{
    return this.http.post<Device>(`${this.url}/devices`,device);
  }

}
