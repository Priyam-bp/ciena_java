import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Shelf } from '../../model/shelf/shelf';

@Injectable({
  providedIn: 'root'
})
export class ShelfService {
  http = inject(HttpClient);

  getAllShelves(){
    const url = "http://localhost:8080/shelf"
    return this.http.get<Array<Shelf>>(url);
  }
}
