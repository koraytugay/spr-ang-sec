import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-foo',
  templateUrl: './foo.component.html'
})
export class FooComponent implements OnInit {

  constructor(private httpClient: HttpClient) {
  }

  msg: string = '';

  ngOnInit(): void {
    this.httpClient.get('http://localhost:8080/foo',
      {withCredentials: true, responseType: 'text'}).subscribe(value => {
      this.msg = value;
    })
  }

}
