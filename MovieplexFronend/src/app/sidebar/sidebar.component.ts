import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  isSidebarExpanded: boolean = false;
  enter : boolean =false;

  constructor(private router: Router){}

  expandSidebar(): void {
    this.isSidebarExpanded = true;
  }

  collapseSidebar(): void {
    this.isSidebarExpanded = false;
  }

  access(){
    if(localStorage.getItem('jwtKey')){
      this.enter =true;
    }
    else{
      alert('Please login first');
      this.router.navigateByUrl('/login');
    }
  }

}
