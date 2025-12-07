import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Category} from "../../models/category";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = "http://localhost:8081/api/auth/";

  constructor(private http: HttpClient) { }

  signUp(username: string , password: string): Observable<any>
  {
    return this.http.post<any>(this.baseUrl + "signUp", { username  , password})
      .pipe( map(response => response ));
  }

  logout()
  {
    sessionStorage.removeItem("token");
  }


  isUserLogin()
  {
    return  sessionStorage.getItem("token") &&
      sessionStorage.getItem("token") !== undefined &&
      sessionStorage.getItem("token") !== null ;
  }
}
