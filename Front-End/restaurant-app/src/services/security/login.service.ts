import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = "http://localhost:8081/api/auth/";

  constructor(private http: HttpClient) { }

  login(username: string , password: string): Observable<any>
  {
    return this.http.post<any>(this.baseUrl + "login", { username  , password})
      .pipe( map(response => response ));
  }
}
