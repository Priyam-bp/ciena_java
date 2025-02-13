import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Shelf } from '../../model/shelf/shelf';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShelfService {
  http = inject(HttpClient);

  url = "http://localhost:8080"
  
  getAllShelves(){
    return this.http.get<Array<Shelf>>(`${this.url}/shelf`);
  }

  saveShelf(shelf: Shelf): Observable<Shelf>{
    return this.http.post<Shelf>(`${this.url}/shelf`,shelf);
  }
}
