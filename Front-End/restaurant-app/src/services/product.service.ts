import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Product} from "../models/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  baseUrl = "http://localhost:8081/api/products";
  constructor(private http: HttpClient) {}

  getAllProduct(pageNamer: number , pageSize: number):Observable<any>
  {
    return this.http.get<Product[]>(this.baseUrl + "/getAllProducts?pageNumber=" +pageNamer + "&pageSize=" +pageSize )
      .pipe(map(response => response));
  }



  getAllProductByCategoryId(id: number , pageNamer: number , pageSize: number):Observable<any>
  {
    return this.http.get<any>(this.baseUrl + "/getAllProdByCatId/" + id + "/" + pageNamer + "/" + pageSize )
      .pipe(map(response => response));
  }




  getProductBySearch(key: string ,pageNamer: number , pageSize: number):Observable<any>
  {
    return this.http.get<Product[]>(this.baseUrl + "/searchProducts?key=" + key + "&pageNumber=" + pageNamer + "&pageSize=" +pageSize )
      .pipe(map(response => response));
  }



  getProductById(id: any):Observable<any>
  {
    return this.http.get<any>(this.baseUrl +"/getById?id=" + id).pipe(map(response => response));
  }



  addProduct(name: string , imagePath: string ,description:string ,categoryName:string ,price:number):Observable<any>
  {
    return this.http.post(this.baseUrl + "/saveProduct", {name ,imagePath ,description ,categoryName ,price}).pipe(map(response => response))
  }


  updateProduct(pro:any):Observable<any>
  {
    return this.http.put(this.baseUrl + "/updateProduct", pro).pipe(map(response => response))
  }


  deleteProduct(name: string ):Observable<any>
  {
    return this.http.delete(this.baseUrl + "/deleteProduct?productName=" + name).pipe(map(response => response))
  }


}
