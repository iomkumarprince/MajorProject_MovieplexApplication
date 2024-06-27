import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ContactService } from '../service/contact.service';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  name:any;
  email:any;
  otp:number = 0;
  otpFromEmail:any;
  password = "";
  conPassword = "";
  show: boolean = true;
  showOtp: boolean = false;
  resetPassword: boolean = false;
  resetData!: FormGroup;

  constructor(private formBuilder: FormBuilder, private login: LoginService, private _snackBar: MatSnackBar,
    private contact : ContactService) { }

  ngOnInit() {
    this.resetData = this.formBuilder.group({
      password: ['', [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$/)]],
      confirmPassword: ['']
    }, {
      validators: this.mustMatch('password', 'confirmPassword')
    });
  }

  getOtp() {
    this.login.getUserByEmail(this.email).subscribe(
      response => {
        this.email = response;
        this.name = this.email.name;
        this.email = this.email.email;
        
        //Write one method to send otp to this email and method should return the otp and store in in a variable
        this.contact.sendOtp(this.name, this.email).subscribe(
          response =>{
            this.otpFromEmail = response;
            this._snackBar.open('Please find the 4-digit otp on your email!', '', {
              duration: 5000,
              panelClass: ['mat-toolbar', 'mat-primary']
            });
          }
        )

        setTimeout(() => {
          // Code to execute after 3 minutes
          this._snackBar.open('Session Expired!', '', {
            duration: 5000,
            panelClass: ['mat-toolbar', 'mat-primary']
          });
          
          this.otpFromEmail = 0;
        }, 3 * 60 * 1000);



        this.show = false;
        this.showOtp = true;
      },
      error =>{
        this._snackBar.open(
          'No User Found with the provided Email address!',
          '',
          {
            duration: 3000,
            panelClass: 'warning-snackbar' // Use a string instead of an array
          }
        );
      }
    )
  }

  submit() {
    if(this.otp == this.otpFromEmail){
      this.showOtp = false;
      this.resetPassword = true;
    }
    else{
      alert(`Invalid Otp! \nPlease Enter the valid 4-digit code`)
    }
  }


  // Custom validator to check if passwords match
  mustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ mustMatchValidator: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }


  reset() {
      this.login.resetPassword(this.email, this.resetData.get('password')?.value).subscribe(
        response => {
          this._snackBar.open('Password reset Successfull!', '', {
            duration: 5000,
            panelClass: ['mat-toolbar', 'mat-primary']
          });
          this.resetData.reset();
          location.reload();
        },
        error => {
          alert('Something went wrong!');
        }
      )
  }

  resend(){
    this.getOtp();
  }
}