import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Category} from "../../models/category";
import {UserIdService} from "../user-id.service";
import {ContactInfo} from "../../models/contact-info";

@Injectable({
  providedIn: 'root'
})
export class ContactInfoService {

  private baseUrl = "http://localhost:8081/api/contactInfo/";

  constructor(private http: HttpClient ,private userId: UserIdService) { }


  addContactInfo(name:string  ,email:string ,subject:string , message:string): Observable<any>
  {
    const userId = this.userId.getCurrentUserId();

    return this.http.post<any>(this.baseUrl + "save", { name,email,subject,message ,userId });

    /* return this.http.post<any>(this.baseUrl + "save", { name,email,subject,message})
      .pipe( map(response => response ));

    */
  }


  showAllContact(): Observable<ContactInfo[]>
  {
    return this.http.get<ContactInfo[]>(this.baseUrl + "getAll" ).pipe(map(response => response));
  }



  showMyContact(): Observable<ContactInfo[]>
  {
    return this.http.get<ContactInfo[]>(this.baseUrl + "getById" ).pipe(map(response => response));
  }


  /*replyMessage():Observable<ContactInfo>
  {
    return this.http.put<ContactInfo>(this.baseUrl + "getAll" ).pipe(map(response => response))
  }

*/


  replyMessages(contact: ContactInfo): Observable<ContactInfo>
  {
    return this.http.put<ContactInfo>(this.baseUrl + "reply" , contact ).pipe(map(response => response));
  }



}
