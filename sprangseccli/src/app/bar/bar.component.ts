import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-bar',
  templateUrl: './bar.component.html'
})
export class BarComponent implements OnInit {

  constructor(private httpClient: HttpClient) {
  }

  msg: string = '';

  ngOnInit(): void {
    let headers = new HttpHeaders();
    headers = headers.append("X-Requested-With", "XMLHttpRequest");
    this.httpClient.get('http://localhost:8080/bar', {headers, responseType: 'text', withCredentials: true})
      .subscribe(value => {
        this.msg = value;
      })
  }

}
