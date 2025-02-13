import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ShelfPosition } from '../../model/shelfPosition/shelf-position';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShelfPositionService {
  http = inject(HttpClient);
  url = 'http://localhost:8080'

  getAllShelfPositions(){
    return this.http.get<Array<ShelfPosition>>(`${this.url}/shelfposition`);
  }
  
  saveShelfPosition(shelfPosition: ShelfPosition): Observable<ShelfPosition>{
    return this.http.post<ShelfPosition>(`${this.url}/shelfposition`,shelfPosition);
  }
}
