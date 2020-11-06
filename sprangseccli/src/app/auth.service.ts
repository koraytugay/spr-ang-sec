import { EventEmitter, Injectable } from '@angular/core';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated: boolean = false;
  authenticationResultEvent = new EventEmitter<boolean>();
  role: string;

  constructor(private dataService: DataService) {
  }

  authenticate(username: string, password: string) {
    this.dataService.validateUser(username, password).subscribe(
      () => {
        this.isAuthenticated = true;
        this.authenticationResultEvent.emit(true);
        this.dataService.getRole().subscribe(response => {
          this.role = response.role;
        });
      }, (error) => {
        console.log(error);
        this.isAuthenticated = false;
        this.authenticationResultEvent.emit(false);
      });
  }

  getRole(): string {
    if (this.isAuthenticated != null) {
      return this.role;
    }
    return null;
  }

  isUserAuthenticated() {
    this.dataService.getRole().subscribe(response => {
      this.isAuthenticated = true;
      this.role = response.role;
      this.authenticationResultEvent.emit(true);
    }, () => {
      // swallow
    });
  }
}
