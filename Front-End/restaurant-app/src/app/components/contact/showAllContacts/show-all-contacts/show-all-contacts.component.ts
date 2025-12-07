import { Component } from '@angular/core';
import { ContactInfoService } from "../../../../../services/contactInfo/contact-info.service";
import { ContactInfo } from "../../../../../models/contact-info";

@Component({
  selector: 'app-show-all-contacts',
  templateUrl: './show-all-contacts.component.html',
  styleUrls: ['./show-all-contacts.component.css']
})
export class ShowAllContactsComponent {

  messageAr: string = "";
  messageEn: string = "";
  allContacts: ContactInfo[] = [];

  userId: any ;

  constructor(private contactService: ContactInfoService) {}

  showAllContacts() {
    this.contactService.showAllContact().subscribe(
      result => {
        // ترتيب الرسائل: pending فوق وreplied تحت
        this.allContacts = result.sort((a:any, b:any) => (a.replyMessage ? 1 : -1));
      },
      error => {
        this.messageAr = error.error.message_ar || "حدث خطأ";
        this.messageEn = error.error.message_en || "Something went wrong";
        setTimeout(() => { this.messageAr = ""; this.messageEn = ""; }, 5000);
      }
    );
  }

  replyMessage(contact: ContactInfo) {
    const tempReply = contact.replyMessageTemp;
    this.contactService.replyMessages({ ...contact, replyMessage: tempReply }).subscribe(
      () => {
        contact.replyMessage = tempReply;
        contact.replyMessageTemp = '';

        this.allContacts = this.allContacts.sort((a, b) => (a.replyMessage ? 1 : -1));
        this.messageAr = "تم إرسال الرد بنجاح";
        this.messageEn = "Reply sent successfully";
        setTimeout(() => { this.messageAr = ""; this.messageEn = ""; }, 5000);
      },
      error => {
        this.messageAr = error.error.message_ar || "حدث خطأ أثناء الرد";
        this.messageEn = error.error.message_en || "Error while sending reply";
        setTimeout(() => { this.messageAr = ""; this.messageEn = ""; }, 5000);
      }
    );
  }

  get pendingContacts() {
    return this.allContacts.filter(c => !c.replyMessage);
  }

  get repliedContacts() {
    return this.allContacts.filter(c => c.replyMessage);
  }
}
