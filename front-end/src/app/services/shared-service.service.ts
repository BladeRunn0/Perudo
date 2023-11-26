import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private nbComputersSubject = new BehaviorSubject<number>(0);
  nbComputers$: Observable<number> = this.nbComputersSubject.asObservable();

  constructor() { }

  setNbComputers(value: number) {
    this.nbComputersSubject.next(value);
  }

  getNbComputers(): Observable<number> {
    return this.nbComputers$;
  }
}
