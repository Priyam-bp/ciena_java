import { inject, Injectable } from '@angular/core';
import { Device } from '../model/device';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  http = inject(HttpClient);
  // deviceItems : Array<Device> = [{
  //   id:1,
  //   name:"device 1",
  //   deviceType:"device type 1"
  // }]

    getAllDevices(){
      const url = `http://localhost:8080/devices`
      return this.http.get<Array<Device>>(url);
    }
}
