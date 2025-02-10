import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ShelfPosition } from '../../model/shelfPosition/shelf-position';

@Injectable({
  providedIn: 'root'
})
export class ShelfPositionService {
  http = inject(HttpClient);

  getAllShelfPositions(){
    const url = 'http://localhost:8080/shelfposition'
    return this.http.get<Array<ShelfPosition>>(url);
  }
  
}
