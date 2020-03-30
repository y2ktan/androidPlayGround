import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { error } from 'protractor';

@Component({
	selector: 'employee-list',
  templateUrl: 'employee-list.component.html',
  styleUrls: ['employee-list.component.css']
})

export class EmployeeListComponent implements OnInit {
  public employees = [];
  public errorMsg:string;

  ngOnInit() {
    // data arrive asynchronous
    this._employeeService.getEmployees()
    .subscribe(data => this.employees = data,
                error=>this.errorMsg = error
      );
  }

  constructor(private _employeeService: EmployeeService){}

  // public employees = [
  //   {"id": 1, "name":"andrew", "age":30},
  //   {"id": 2, "name":"fancy", "age":38},
  //   {"id": 3, "name":"brad", "age":20},
  // ];
}
