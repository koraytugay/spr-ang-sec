import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) {
  }

  validateUser(username: string, password: string): Observable<void> {
    const authData = btoa(`${username}:${password}`);  // binary to ascii - encodes input in base64
    let headers = new HttpHeaders().append('Authorization', 'Basic ' + authData);
    headers = headers.append("X-Requested-With", "XMLHttpRequest");
    return this.http.get<void>('http://localhost:8080/validate',
      {headers: headers, withCredentials: true});
  }

  validateUserSession(): Observable<void> {
    let headers = new HttpHeaders();
    headers = headers.append("X-Requested-With", "XMLHttpRequest");
    return this.http.get<void>('http://localhost:8080/validate', {headers: headers, withCredentials: true});
  }
}
