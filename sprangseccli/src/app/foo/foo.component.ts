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
    let headers = new HttpHeaders();
    headers = headers.append("X-Requested-With", "XMLHttpRequest");
    this.httpClient.get('http://localhost:8080/foo',
      {responseType: 'text', withCredentials: true, headers: headers})
      .subscribe(value => {
        this.msg = value;
        this.yourRole = this.authService.getRole();
      })
  }
}
