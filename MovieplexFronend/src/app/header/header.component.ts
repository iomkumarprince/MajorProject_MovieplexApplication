import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { SignupComponent } from '../signup/signup.component';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { FavouriteService } from '../service/favourite.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  check: boolean = false;
  nameValue: any;
  imagePath: any;
  loginCheck = false;
  flag: boolean = false; 
  userName : String =";"
  welcome: boolean = false;


  searchForm = new FormGroup({
    'movieName': new FormControl(null)
  });
  
  searchFormValue = new FormGroup({
    'movieName': new FormControl(null)
  });

  constructor(public matDialog: MatDialog,  private sanitizer:DomSanitizer, private login : LoginService, private fav:FavouriteService, private router: Router) {}

    ngOnInit(): void {        
      this.userName = this.login.name;  
      if(localStorage.getItem('loginCheck')){
        this.flag = true;
        this.getImage();
      }  
      if(localStorage.getItem('loginCheck') && this.userName != ""){
        this.welcome = true;
    }
       
    }

  openLoginDialog() {
    this.matDialog.open(LoginComponent, {
      minWidth: '400px',     
    });
  }

  openSignupDialog() {
    this.matDialog.open(SignupComponent, {
      maxWidth: '400px',
      maxHeight:'570px',
    });
  }

  logout() {
    this.flag = window.confirm('Are you sure you want to logout?');
    if (this.flag) {
      localStorage.clear();
      this.flag = false;     
      this.router.navigateByUrl("logout");
    }else{
      this.flag = true;
    }
  }

  getImage(){
  this.fav.getProfileImg().subscribe((data:any)=>{
    if(data && data.imageData){
      const imageData = data.imageData;
      const binaryData = atob(imageData);
      const arrayBuffer = new ArrayBuffer(binaryData.length);
      const uint8Array = new Uint8Array(arrayBuffer);
      for (let i = 0; i < binaryData.length; i++) {
        uint8Array[i] = binaryData.charCodeAt(i);
      }
      const blob = new Blob([uint8Array], { type: 'image/png' });
      this.imagePath = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(blob));
    }
  })
}

  values :any;
  getNameFromUser(){
    this.login.getuser().subscribe(response => {
      this.values = response;
    })
  }

  
  expandSearch() {
    const inputField = document.querySelector('.search-input') as HTMLElement; 
    inputField.focus();
  }
  
  submitSearch() {
    // Add logic to handle search submission
  }


  sendValues() {
    this.router.navigate(['/search', this.searchFormValue.value.movieName]);
    setTimeout(() => {
      location.reload();
    }, 100);
  }
}