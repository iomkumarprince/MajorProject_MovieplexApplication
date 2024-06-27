import { Component,Inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit{   

  file: File | undefined;

  constructor(private userService: LoginService,private fb: FormBuilder, private router : Router) {
  
  }
  updateForm = this.fb.group({
    email: ['', ],
    phoneNo: ['', [Validators.required, Validators.pattern('[0-9]{10}')]],
    password: ['', [Validators.required, Validators.minLength(6)]],
  });
 


get email() { return this.updateForm.get("email") }  
get password() { return this.updateForm.get("password") }
get phoneNo() { return this.updateForm.get("phoneNo") }
 

  ngOnInit(): void {
   this.getdetail();  
  }

  onFileSelected(event: any) {
    this.file = event.target.files[0];
  }

  onSubmit() {
    const userEmail = this.updateForm.value.email;
    const userData = this.updateForm.value;

    if (this.file && userEmail && userData) {
      this.userService.updateUserWithEmail(this.file, userEmail, userData).subscribe(
        (response) => {
          console.log('User updated successfully:', response);
          location.reload();
          alert("Update Sucessfully")
          this.router.navigateByUrl('/profile');
          // Handle success, e.g., show a success message to the user
        },
        (error) => {
          console.error('Failed to update user:', error);
          // Handle error, e.g., show an error message to the user

        }
      );
    } else {
      console.error('File, user email, or user data is missing');
      // Handle error, e.g., show an error message to the user
    }
  }
 
values:any;

  getdetail() {
    this.userService.getuser().subscribe(
      data => {
        this.values = data;
        this.updateForm.get('email')?.setValue(this.values.email);
      }
    )
  }}
