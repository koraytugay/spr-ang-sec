import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-bar',
  templateUrl: './bar.component.html'
})
export class BarComponent implements OnInit {

  constructor(private httpClient: HttpClient) {
  }

  msg: string = '';

  ngOnInit(): void {
    this.httpClient.get('http://localhost:8080/bar', {withCredentials: true, responseType: 'text'})
      .subscribe(value => {
        this.msg = value;
      })
  }

}
