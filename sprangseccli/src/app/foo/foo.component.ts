import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-foo',
  templateUrl: './foo.component.html'
})
export class FooComponent implements OnInit {

  constructor(private httpClient: HttpClient, private authService: AuthService) {
  }

  msg: string = '';
  yourRole: string;

  ngOnInit(): void {
    const bearerHeader =
      new HttpHeaders().append('Authorization', 'Bearer ' + this.authService.jwtToken);

    this.httpClient.get('http://localhost:8080/foo', {responseType: 'text', headers: bearerHeader})
      .subscribe(value => {
        this.msg = value;
        this.yourRole = this.authService.getRole();
      })
  }
}
