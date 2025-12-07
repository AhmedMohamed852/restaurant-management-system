import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ContactInfoService} from "../../../../services/contactInfo/contact-info.service";

@Component({
  selector: 'app-contact-info',
  templateUrl: './contact-info.component.html',
  styleUrls: ['./contact-info.component.css']
})
export class ContactInfoComponent implements OnInit {

  constructor(private contact: ContactInfoService , private activatedRoute: ActivatedRoute , private router: Router) { }


  messageAr: string = "";
  messageEn: string = "";


  ngOnInit(): void
  {

  }


    addContactInfo(nameUser: string , subject: string, email: string ,message: string):void
    {
      if(!this.validateInputs(nameUser, subject, email, message))
      {
        setTimeout(()=>
        {
          this.messageAr = "";
          this.messageEn = "";
        }, 5000)

        return ;
      }

      this.contact.addContactInfo(nameUser, subject, email, message).subscribe(
        () => {
          this.router.navigateByUrl("/contacts");
        },
        (errorResponse: any) => {
          this.messageAr = errorResponse.error.message_ar;
          this.messageEn = errorResponse.error.message_en;
        }
      )
    }



  //TODO _______________validateInputs_________________________
  validateInputs(userName: string, subject: string, email: string, message: string): boolean {

    if (!userName) {
      this.messageAr = "الرجاء إدخال اسمك";
      this.messageEn = "Please enter your name";
      return false;
    }

    if (!subject) {
      this.messageAr = "الرجاء إدخال الموضوع";
      this.messageEn = "Please enter the subject";
      return false;
    }

    if (!email) {
      this.messageAr = "الرجاء إدخال البريد الإلكتروني";
      this.messageEn = "Please enter your email";
      return false;
    }

    if (!message) {
      this.messageAr = "الرجاء إدخال الرسالة";
      this.messageEn = "Please enter your message";
      return false;
    }

    // لو كل شيء صحيح، امسح الرسائل
    this.messageAr = "";
    this.messageEn = "";
    return true;
  }



  goToNotifications()
  {
      this.router.navigateByUrl("/showMyContact");
  }





}
