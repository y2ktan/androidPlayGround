import { Component, OnInit } from '@angular/core';

@Component({
  selector: '[app-test]',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  public name ="Tan Chun Mun";
  public myId ="testId";
  public isEnabled = false;
  constructor() { }

  ngOnInit() {
  }

  greetUser(){
    return "Hello "+this.name;
  }

}
