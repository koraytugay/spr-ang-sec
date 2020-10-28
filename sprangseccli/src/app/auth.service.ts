import { EventEmitter, Injectable } from '@angular/core';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated: boolean = false;
  authenticationResultEvent = new EventEmitter<boolean>();

  constructor(private dataService: DataService) {
  }

  authenticate(username: string, password: string) {
    this.dataService.validateUser(username, password).subscribe(
      () => {
        this.isAuthenticated = true;
        this.authenticationResultEvent.emit(true);
      }, (error) => {
        console.log(error);
        this.isAuthenticated = false;
        this.authenticationResultEvent.emit(false);
      });
  }

  validateSession() {
    this.dataService.validateUserSession().subscribe(() => {
      this.isAuthenticated = true;
      this.authenticationResultEvent.emit(true);
    }, () => {
      this.isAuthenticated = false;
    })
  }
}
