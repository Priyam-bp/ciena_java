import { Component, inject, OnInit, signal } from '@angular/core';
import { ShelfService } from '../../services/shelfService/shelf-service.service';
import { Shelf } from '../../model/shelf/shelf';
import { catchError, of } from 'rxjs';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-shelf',
  imports: [],
  templateUrl: './shelf.component.html',
  styleUrl: './shelf.component.css'
})
export class ShelfComponent implements OnInit{
  shelfService = inject(ShelfService);
  shelfItems = signal<Array<Shelf>>([]);

  ngOnInit(): void {
      this.shelfService.getAllShelves()
      .pipe(catchError((err)=>{
        console.log(err);
        return of([]);
      }))
      .subscribe((shelf)=>{
        this.shelfItems.set(shelf);
      })
  }
}
