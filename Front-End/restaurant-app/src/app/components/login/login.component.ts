import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../../services/security/login.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserIdService} from "../../../services/user-id.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  messageAr: string = "";
  messageEn: string = "";

  constructor(private loginService: LoginService, private router: Router ,private activatedRoute: ActivatedRoute,
              private userId: UserIdService) {}

  ngOnInit(): void {
    this.reloadPage();
  }


  //TODO _______________loginToAccount_________________________
  login(userName: string,password: string )
  {

    if(!this.validateINputs(userName ,password ))
    {
      // ⭐️⭐️ تم نقل الـ setTimeout إلى هنا للتحكم في مسح رسائل التحقق ⭐️⭐️
      setTimeout(()=>
      {
        this.messageAr ="";
        this.messageEn ="";
      } ,5000)

      return ; // إنهاء العملية إذا فشل التحقق
    }

        this.loginService.login(userName, password).subscribe(
          result =>
          {
            sessionStorage.setItem("token", result.token);
            this.userId.saveUserId(result.id);
            alert(this.userId.getRoles());
            this.router.navigateByUrl("/products").then(r => window.location.reload());
          } ,
            errorResponse =>
          {
            this.messageAr = errorResponse.error.message_ar;
            this.messageEn = errorResponse.error.message_en;

            setTimeout(() =>
            {
              this.messageAr ="";
              this.messageEn ="";
            } , 5000)
          }
      )



  }



  //TODO _______________validateINputs_________________________
  validateINputs(userName: string, password: string)
  {
      if(!userName)
      {
        this.messageAr = "من فضلك أدخل اسم المستخدم";
        this.messageEn = "Please enter username";
        return false;
      }

    if(!password)
    {
        this.messageAr = "من فضلك أدخل كلمة المرور";
        this.messageEn = "Please enter password";
        return false;
    }

    return true;

  }


  reloadPage(): void {
    // تحقق أولاً إذا أردت تحديث صفحة معينة فقط
    if (this.router.url === '/products') {
      window.location.reload();
    }
  }

}
