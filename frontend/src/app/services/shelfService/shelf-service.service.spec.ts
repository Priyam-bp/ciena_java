import { TestBed } from '@angular/core/testing';

import { ShelfServiceService } from './shelf-service.service';

describe('ShelfServiceService', () => {
  let service: ShelfServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShelfServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
