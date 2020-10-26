import { EventEmitter, Injectable } from '@angular/core';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated: boolean = false;
  authenticationResultEvent = new EventEmitter<boolean>();
  jwtToken: string;

  constructor(private dataService: DataService) {
  }

  authenticate(username: string, password: string) {
    this.dataService.validateUser(username, password).subscribe(
      (payload) => {
        this.jwtToken = payload.jwtToken;
        this.isAuthenticated = true;
        this.authenticationResultEvent.emit(true);
      }, (error) => {
        console.log(error);
        this.isAuthenticated = false;
        this.authenticationResultEvent.emit(false);
      });
  }

  getRole(): string {
    if (this.jwtToken != null) {
      const encodedPayload = this.jwtToken.split('.')[1];
      const payload = atob(encodedPayload);
      return JSON.parse(payload).role;
    }
  }
}
