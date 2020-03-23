import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'employee-list',
  templateUrl: 'employee-list.component.html',
  styleUrls: ['employee-list.component.css']
})

export class EmployeeListComponent implements OnInit {

  ngOnInit() { }

  public employees = [
    {"id": 1, "name":"andrew", "age":30},
    {"id": 2, "name":"fancy", "age":38},
    {"id": 3, "name":"brad", "age":20},
  ]
}
