import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelfPositionListComponent } from './shelf-position-list.component';

describe('ShelfPositionListComponent', () => {
  let component: ShelfPositionListComponent;
  let fixture: ComponentFixture<ShelfPositionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShelfPositionListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShelfPositionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
