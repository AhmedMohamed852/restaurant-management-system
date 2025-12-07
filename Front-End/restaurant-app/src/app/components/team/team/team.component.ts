import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TeamService} from "../../../../services/team.service";
import {Team} from "../../../../models/team";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {

  teams: Team[] = [];

    constructor(private team: TeamService ,private activatedRoute: ActivatedRoute) { }
    ngOnInit(): void
    {
      this.activatedRoute.paramMap.subscribe(params => {
        this.getAllTeam()
      })
    }

    getAllTeam(){
      this.team.getAllTeam().subscribe(
       value => this.teams = value
      )
    }

}
