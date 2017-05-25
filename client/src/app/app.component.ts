import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  public showingDialog:boolean = false;

  public showDialog() {
    this.showingDialog = true;
  }

  public hideDialog() {
    this.showingDialog = false;
  }

}
