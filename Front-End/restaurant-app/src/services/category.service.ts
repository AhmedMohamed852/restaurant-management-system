import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Category } from "../models/category";
import { Observable, map } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = "http://localhost:8081/api/categories";

  constructor(private http: HttpClient) { }

  getAllCategories(): Observable<Category[]>
  {
    return this.http.get<Category[]>(this.baseUrl + "/getAll")
      .pipe( map(response => response ));
  }


}
