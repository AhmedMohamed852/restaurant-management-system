import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Category} from "../../models/category";

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {

  private baseUrl = "http://localhost:8081/api/users/";

  constructor(private http: HttpClient) { }

  isUserHaseDetails():Observable<boolean>
  {
    return this.http.get<boolean>(this.baseUrl + "isUserHasDetails" )
      .pipe(map(response => response));
  }



  updateUserDetails(userDetails: any):Observable<any>
  {
    return this.http.post<any>(this.baseUrl + "addUserDetail",userDetails);
  }

}
