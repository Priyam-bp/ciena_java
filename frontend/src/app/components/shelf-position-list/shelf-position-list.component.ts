import { Component, inject, OnInit, signal } from '@angular/core';
import { ShelfPositionService } from '../../services/shelfPositionService/shelf-position-service.service';
import { ShelfPosition } from '../../model/shelfPosition/shelf-position';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-shelf-position-list',
  imports: [],
  standalone: true,
  templateUrl: './shelf-position-list.component.html',
  styleUrl: './shelf-position-list.component.css'
})
export class ShelfPositionListComponent implements OnInit {

  shelfPositionService = inject(ShelfPositionService);
  shelfPositionItems = signal<Array<ShelfPosition>>([]);

  ngOnInit(): void {
      this.shelfPositionService.getAllShelfPositions()
        .pipe(
          catchError((err)=>{
            console.log(err);
            return of([]);
          })
        )
        .subscribe((shelfPositions)=>{
          this.shelfPositionItems.set(shelfPositions);
          console.log(shelfPositions);
          
        })
  }
}
