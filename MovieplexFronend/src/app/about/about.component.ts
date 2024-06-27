import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  constructor(){
    // Add this code in the component where you want to navigate from
    window.scrollTo(0, 0);
  }
  
  navigateToGmail(recipientEmail:any) {
    const subject = 'Your subject';
    const body = 'Your email body';
  
    const encodedEmail = encodeURIComponent(recipientEmail);
    const encodedSubject = encodeURIComponent(subject);
    const encodedBody = encodeURIComponent(body);
  
    const gmailLink = `https://mail.google.com/mail/?view=cm&to=${encodedEmail}&su=${encodedSubject}&body=${encodedBody}`;
    window.open(gmailLink, '_blank');
  }
}
