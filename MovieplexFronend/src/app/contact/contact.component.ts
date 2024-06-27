import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ContactService } from '../service/contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent {
  contactForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,private contactService:ContactService,private _snackBar:MatSnackBar) { }

  ngOnInit() {
    this.contactForm = this.formBuilder.group({
      fullName: ['', Validators.required],
      userEmail: ['', [Validators.required, Validators.email]],
      text: ['', Validators.required]
    });
  }

  get fullName() {
    return this.contactForm.get('fullName');
  }

  get userEmail() {
    return this.contactForm.get('userEmail');
  }

  get text() {
    return this.contactForm.get('text');
  }

  onSubmit() {
     this.contactService.contactData(this.contactForm.value).subscribe(
      response=>{
        alert(JSON.stringify(response));
        this._snackBar.open('Congrats, you have successfully Submit your query !!!', '', {
          duration: 5000,
          panelClass: ['mat-toolbar', 'mat-primary']
          
        });
        this.contactForm.reset();
      },
      error => {
        alert('Something Went Wrong! \nPlease try again!!!');
        console.log(error);
        
      }
     )
  }
  contact(){
      this._snackBar.open('Please wait for sometime, your ticket is being raised!!!', '', {
        duration: 9000,
        panelClass: ['mat-toolbar', 'mat-primary']
      });
  }
}
