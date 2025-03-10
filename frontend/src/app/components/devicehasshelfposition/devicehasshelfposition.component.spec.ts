import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DevicehasshelfpositionComponent } from './devicehasshelfposition.component';

describe('DevicehasshelfpositionComponent', () => {
  let component: DevicehasshelfpositionComponent;
  let fixture: ComponentFixture<DevicehasshelfpositionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DevicehasshelfpositionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DevicehasshelfpositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
