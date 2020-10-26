import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) {
  }

  validateUser(username: string, password: string): Observable<{jwtToken:string}> {
    const authData = btoa(`${username}:${password}`);  // binary to ascii - encodes input in base64
    const headers = new HttpHeaders().append('Authorization', 'Basic ' + authData);
    return this.http.get<{jwtToken:string}>('http://localhost:8080/validate',
      {headers: headers, withCredentials: true});
  }
}
