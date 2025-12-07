import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {observableToBeFn} from "rxjs/internal/testing/TestScheduler";
import {Team} from "../models/team";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  baseUrl = "http://localhost:8081/api/chef";
  constructor(private http: HttpClient) { }

  getAllTeam():Observable<Team[]>
  {
    return  this.http.get<Team[]>(this.baseUrl + "/getAll").
    pipe(map(response => response))
  }


}
