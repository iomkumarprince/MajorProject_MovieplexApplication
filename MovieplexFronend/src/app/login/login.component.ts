import { Component, Inject } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DialogRef, DIALOG_DATA } from '@angular/cdk/dialog';
import { LoginService } from '../service/login.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {

  

  constructor(public dialogRef: DialogRef, @Inject(DIALOG_DATA) private router: Router, private dialog: MatDialog, private login : LoginService,
    private fb: FormBuilder) { }

  loginForm!: FormGroup
  email: string = "";
  tokenData: any;
  check:string = "true";

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ["", [Validators.required, this.checkIfGuestEmailsAreValid, Validators.pattern(/^[a-zA-Z0-9._%+-]+@(gmail|yahoo)\.com$/)]],
      password: ["", [Validators.minLength(4), Validators.required]]
    })
  }

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

  onSubmit() {
    this.login.loginCheck(this.loginForm.value).subscribe( 
      response => { 
        this.tokenData = response;
      
        localStorage.setItem('jwtKey', this.tokenData.token);  
        localStorage.setItem('loginCheck', this.check);
        alert("Login Success");
         location.reload();
         this.router.navigateByUrl('/dashboard');
        //  alert(JSON.stringify(this.tokenData.token));
    },
    error=>
      alert(JSON.stringify(error))
    )
  } 

  goToF_Pass(){
    const dialogRef: MatDialogRef<ForgotPasswordComponent> = this.dialog.open(ForgotPasswordComponent, {
      width: '400px',
      disableClose: false,
      data: {
      }
    });
  }

}
