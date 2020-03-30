import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { IEmployee } from './employee';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
//import 'rxjs/add/operator/catch';
//import 'rxjs/add/observable/throw';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private _url: string = "/assets/data/employees.json";

  constructor(private http: HttpClient) { }

  getEmployees(){
    return this.http.get<IEmployee[]>(this._url)
    .pipe(
      catchError(this.errorHandler)
    );
    //.catch(this.errorHandler);
  }

  errorHandler(error: HttpErrorResponse){
    return throwError(error.message||"server error");
  }
}
