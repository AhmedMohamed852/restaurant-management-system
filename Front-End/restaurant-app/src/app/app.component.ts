import { Component, AfterViewInit } from '@angular/core';
import {AuthService} from "../services/security/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {

  constructor(private authService: AuthService) {
  }

  ngAfterViewInit(): void {
    // بعد تحميل كل عناصر الصفحة
    const spinner = document.getElementById('spinner');
    if (spinner) {
      spinner.style.display = 'none'; // يخفي الـ spinner
    }
  }

  isUserLogin()
  {
    return  sessionStorage.getItem("token") &&
      sessionStorage.getItem("token") !== undefined &&
      sessionStorage.getItem("token") !== null ;
  }

}
