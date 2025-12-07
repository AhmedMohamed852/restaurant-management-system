import {Component, OnInit} from '@angular/core';
import {UserDetailsService} from "../../../services/userDetails/user-details.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  messageAr: string = "";
  messageEn: string = "";

    ngOnInit(): void
    {

    }

    constructor(private userDetails: UserDetailsService ,private router:Router) {}




    updateUserDetails(userDetails: any)
    {
      this.userDetails.updateUserDetails(userDetails).subscribe(()=>
      {
        this.router.navigate(['products']);
      }  ,
        errorResponse =>
        {
          this.messageAr = errorResponse.error.message_ar;
          this.messageEn = errorResponse.error.message_en;

          setTimeout(() =>
          {
            this.messageAr ="";
            this.messageEn ="";
          } , 5000)
        })
    }

}
