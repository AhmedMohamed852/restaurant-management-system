import { Component, OnInit } from '@angular/core';
import { ContactInfoService } from "../../../../services/contactInfo/contact-info.service";
import { Router } from "@angular/router";
import { ContactInfo } from "../../../../models/contact-info";

@Component({
  selector: 'app-show-my-contact',
  templateUrl: './show-my-contact.component.html',
  styleUrls: ['./show-my-contact.component.css']
})
export class ShowMyContactComponent implements OnInit {

  myContact: ContactInfo[] = [];
  messageAr: string = "";
  messageEn: string = "";

  constructor(private contactService: ContactInfoService, private router: Router) {}

  ngOnInit() {
    this.loadMyContacts();
  }

  loadMyContacts() {
    this.contactService.showMyContact().subscribe(
      (values: ContactInfo[]) => {
        // ترتيب الرسائل: غير المقروءة فوق
        this.myContact = values.sort((a, b) => {
          if (!a.replyMessage && b.replyMessage) return -1; // غير مقروءة فوق
          if (a.replyMessage && !b.replyMessage) return 1;  // المقروءة تحت
          return 0;
        });
      },
      (errorResponse) => {
        this.messageAr = errorResponse.error.message_ar || "حدث خطأ";
        this.messageEn = errorResponse.error.message_en || "Something went wrong";
      }
    );
  }

}
