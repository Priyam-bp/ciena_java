import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Device } from '../../model/device/device';
import { Observable } from 'rxjs';
import { Addshelfpositionrequest } from '../../model/addShelfPositionRequest/addshelfpositionrequest';


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

  addShelfPositionToDevice(addshelfpositionrequest: Addshelfpositionrequest){
    return this.http.post<Addshelfpositionrequest>(`${this.url}/devices/addShelfPositionToDevice`, addshelfpositionrequest);
  }

  editDevice(id: number,deviceData : Device){
    return this.http.put(`${this.url}/devices/${id}`,deviceData)
  }

  deleteDevice(id:number){
    return this.http.delete(`${this.url}/devices/${id}`,{responseType:'text'});
  }

  getShelfAndShelfPositions(){
    return this.http.get(`${this.url}/shelf/getShelfWithShelfPositions`);
  }
}