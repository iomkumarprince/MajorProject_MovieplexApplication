import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomValidator } from '../validators/customvalidator';

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.css']
})

export class SignupComponent {

    name: string = "";
    email: string = "";
    password: string = "";
    confirmPassword: string = "";
    phoneNo: string = "";
    responseData: any;
    public uploadedImage: any = File;
    show:boolean=true;
    shown:boolean=true;

    constructor(private fb: FormBuilder, private login: LoginService, private _snackBar: MatSnackBar, private router: Router) { }

    registrationForm = this.fb.group({
      name: ["",  [Validators.required, Validators.pattern('^[a-zA-Z ]*$'), Validators.maxLength(30)]],
      email: ["", [Validators.required, this.checkIfGuestEmailsAreValid, Validators.pattern(/^[a-zA-Z0-9._%+-]+@(gmail|yahoo)\.com$/)]],
      password: ["", [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
      confirmPassword: ["", [Validators.required]],
      phoneNo: ["", [Validators.required, Validators.pattern(/^[789]\d{9,9}$/)]],
      imagePath: [""],
    }, { validators: [CustomValidator.mustMatchValidator] })
  
  
    checkIfGuestEmailsAreValid(c: AbstractControl) {
        if (c.value !== '') {
          const emailString = c.value;
          const emails = emailString.split(',').map((e: string) => e.trim());
          const emailRegex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
          const anyInvalidEmail = emails.every((e: string) => e.match(emailRegex) !== null);
          if (!anyInvalidEmail) {
            return { emailsAreValidCheck: false }
          }
        }
        return;
      } 
      
    public onImageUpload(event: any) {
        const userImg = event.target.files[0];
        this.uploadedImage = userImg;
    }

    addUser() {
        const userData = this.registrationForm.value;
        const fData = new FormData();
        // form data alway supports string file
        fData.append('userData', JSON.stringify(userData));
        fData.append('file', this.uploadedImage);

        this.login.register(fData).subscribe(
            next => {
              this._snackBar.open('Account created successfully.....', 'success', {
                duration: 3000,
                panelClass: ['mat-toolbar', 'blue']
              });   
              this.show=false;
              this.shown=false;      
            },            
            error => {
                alert(JSON.stringify(error));
            })
            this.router.navigateByUrl('');
    }

}


