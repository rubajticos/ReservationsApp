import { TestBed } from '@angular/core/testing';

import { HttpVisitService } from './http-visit.service';

describe('HttpVisitService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpVisitService = TestBed.get(HttpVisitService);
    expect(service).toBeTruthy();
  });
});
