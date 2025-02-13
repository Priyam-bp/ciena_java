import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddShelfpositionComponent } from './add-shelfposition.component';

describe('AddShelfpositionComponent', () => {
  let component: AddShelfpositionComponent;
  let fixture: ComponentFixture<AddShelfpositionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddShelfpositionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddShelfpositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
