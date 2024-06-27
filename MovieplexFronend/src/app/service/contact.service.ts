import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  constructor(private http:HttpClient) { }
  baseUrl:string = "http://localhost:8890/api/contactEmail";

  contactData(contactForm:FormData): Observable<any>{
   return this.http.post<any>(this.baseUrl+"/contact-email",contactForm);
  }

  sendOtp(name:any, email:any){
    return this.http.get("http://localhost:8804/api/v2/sendEmail/forgot-password/"+name+"/"+email,{ responseType: 'text' });
  }
}
