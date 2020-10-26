import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-bar',
  templateUrl: './bar.component.html'
})
export class BarComponent implements OnInit {

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  msg: string = '';

  ngOnInit(): void {
    const bearerHeader =
      new HttpHeaders().append('Authorization', 'Bearer ' + this.authService.jwtToken);

    this.httpClient.get('http://localhost:8080/bar', {responseType: 'text', headers: bearerHeader})
      .subscribe(value => {
        this.msg = value;
      })
  }

}
