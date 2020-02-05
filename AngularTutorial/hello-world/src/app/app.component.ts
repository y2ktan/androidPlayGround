import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Project CC';
  date = new Date();
  name="Tan Chun Mun";

  // constructor(){
  //   console.log("C called");
  //   this.doSomething('Motorola')
  // }

  // doSomething(val:string): void{
  //   val = "Awesome";
  // }

}
