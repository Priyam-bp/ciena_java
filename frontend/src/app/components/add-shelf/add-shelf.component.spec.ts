import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddShelfComponent } from './add-shelf.component';
import { ToastrModule } from 'ngx-toastr';

describe('AddShelfComponent', () => {
  let component: AddShelfComponent;
  let fixture: ComponentFixture<AddShelfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddShelfComponent,ToastrModule.forRoot({
        positionClass :'toast-bottom-right'
      }) ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddShelfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
