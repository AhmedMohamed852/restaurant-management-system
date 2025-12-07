

import {Router} from "@angular/router";
import {AuthService} from "../../../services/security/auth.service";

import { Component } from '@angular/core';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent  {


  constructor(private router: Router, private authService: AuthService)
  {}

  messageEn:string = "";
  messageAr:string = "";


  //TODO _______________createAccount_________________________
  createAccount(userName: string,password: string , confirmPassword: string)
  {
    if(!this.validateINputs(userName ,password ,confirmPassword))
    {
      setTimeout(()=>
      {
        this.messageAr ="";
        this.messageEn ="";
      } ,5000)
      return ;
    }

    this.authService.signUp(userName, password).subscribe(
      result => { this.router.navigateByUrl("/login");},
        errorResponse =>
      {
        this.messageAr = errorResponse.error[0].message_ar || errorResponse.error.message_ar;
        this.messageEn = errorResponse.error[0].message_en || errorResponse.error.message_en;
      });

    setTimeout(() =>
    {
      this.messageAr = "";
      this.messageEn = "";
    } , 5000)
  }

  //TODO _______________validateINputs_________________________
  validateINputs(userName: string, password: string, confirmPassword: string)
  {
    if(!userName) {
      this.messageAr = "من فضلك أدخل اسم المستخدم";
      this.messageEn = "Please enter username";
      return false;
    }

    if(!password) {
      this.messageAr = "من فضلك أدخل كلمة المرور";
      this.messageEn = "Please enter password";
      return false;
    }

    if(!confirmPassword) {
      this.messageAr = "من فضلك أكد كلمة المرور";
      this.messageEn = "Please confirm your password";
      return false;
    }

    if(password !== confirmPassword) {
      this.messageAr = "كلمتا المرور غير متطابقتين";
      this.messageEn = "Passwords do not match";
      return false;
    }

    return true;
  }


  }



