import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent {

  resetData!: FormGroup;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private login: LoginService,
    private _snackBar: MatSnackBar) { }

  emailId: any;
  ngOnInit() {
    this.emailId = localStorage.getItem('email');
    this.resetData = this.formBuilder.group({
      currentPassword: ['',[Validators.required]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$/)]],
      confirmPassword: ['']
    }, {
      validators: this.mustMatch('password', 'confirmPassword')
    });
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


  resetPassword() {
    if (this.data.password == this.resetData.get('currentPassword')?.value) {
      this.login.resetPassword(this.emailId, this.resetData.get('password')?.value).subscribe(
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
    else{
      alert('You have Entered Wrong Password!');
    }
  }
}
