import { TestBed } from '@angular/core/testing';

import { SecutityService } from './secutity.service';

describe('SecutityService', () => {
  let service: SecutityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SecutityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
