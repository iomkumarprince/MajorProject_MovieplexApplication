import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MoviesService } from '../service/movies.service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  typeof(arg0: any): any {
    throw new Error('Method not implemented.');
  }
  constructor(private movies: MoviesService, private router: Router) { }
  movieList: any = [];
  actionMovies: any = [];
  comedyMovies: any = [];
  animatedMovies: any = [];
  documentaryMovies: any = [];
  scienceFictionMovies: any = [];
  thrillerMovies: any = [];
  nameValue:any;
  showHeaderText:boolean = false;
  profileCheck:boolean = false;
  video!: HTMLVideoElement;

  ngOnInit() {
    this.getMovies();
    this.getActionMovies();
    this.getComedyMovies();
    this.getanimatedMovies();
    this.getdocumentaryMovies();
    this.getscienceFictionMovies();
    this.getThrillerMovies();
    this.nameValue = localStorage.getItem('name');
    if(this.nameValue != null || this.nameValue != undefined)
    {
      this.profileCheck= true;
    }   
  }

  getMovies() {
    this.movies.getMovies().subscribe(
      response => {
        this.movieList = response;
        this.movieList = this.movieList.results;
      }
    )
  }
  getActionMovies() {
    this.movies.fetchActionMovies().subscribe(
      response => {
        this.actionMovies = response;
        this.actionMovies = this.actionMovies.results;
        console.log(this.actionMovies);

      }
    )
  }
  go(item: any) {
    this.movies.item = item;
    this.router.navigateByUrl("movies");
  }

  @ViewChild('cardContainer') cardContainer!: ElementRef;
  @ViewChild('cardContainer1') cardContainer1!: ElementRef;
  @ViewChild('cardContainer2') cardContainer2!: ElementRef;
  @ViewChild('cardContainer3') cardContainer3!: ElementRef;
  @ViewChild('cardContainer4') cardContainer4!: ElementRef;
  @ViewChild('cardContainer5') cardContainer5!: ElementRef;
  @ViewChild('cardContainer6') cardContainer6!: ElementRef;

  scrollStep: number = 500; // Adjust this value as needed

  scroll(direction: 'left' | 'right') {
    const container = this.cardContainer.nativeElement as HTMLElement;

    if (direction === 'left') {
      container.scrollTo({
        left: container.scrollLeft - this.scrollStep,
        behavior: 'smooth'
      });
    } else {
      container.scrollTo({
        left: container.scrollLeft + this.scrollStep,
        behavior: 'smooth'
      });
    }
  }

  scroll1(direction: 'left' | 'right') {
    const container1 = this.cardContainer1.nativeElement as HTMLElement;

    if (direction === 'left') {
      container1.scrollTo({
        left: container1.scrollLeft - this.scrollStep,
        behavior: 'smooth'
      });
    } else {
      container1.scrollTo({
        left: container1.scrollLeft + this.scrollStep,
        behavior: 'smooth'
      });
    }
  }

  scroll2(direction: 'left' | 'right') {
    const container2 = this.cardContainer2.nativeElement as HTMLElement;

    if (direction === 'left') {
      container2.scrollTo({
        left: container2.scrollLeft - this.scrollStep,
        behavior: 'smooth'
      });
    } else {
      container2.scrollTo({
        left: container2.scrollLeft + this.scrollStep,
        behavior: 'smooth'
      });
    }
  }

  scroll3(direction: 'left' | 'right') {
    const container3 = this.cardContainer3.nativeElement as HTMLElement;

    if (direction === 'left') {
      container3.scrollTo({
        left: container3.scrollLeft - this.scrollStep,
        behavior: 'smooth'
      });
    } else {
      container3.scrollTo({
        left: container3.scrollLeft + this.scrollStep,
        behavior: 'smooth'
      });
    }
  }

  scroll4(direction: 'left' | 'right') {
    const container4 = this.cardContainer4.nativeElement as HTMLElement;

    if (direction === 'left') {
      container4.scrollTo({
        left: container4.scrollLeft - this.scrollStep,
        behavior: 'smooth'
      });
    } else {
      container4.scrollTo({
        left: container4.scrollLeft + this.scrollStep,
        behavior: 'smooth'
      });
    }
  }

  scroll5(direction: 'left' | 'right') {
    const container5 = this.cardContainer5.nativeElement as HTMLElement;

    if (direction === 'left') {
      container5.scrollTo({
        left: container5.scrollLeft - this.scrollStep,
        behavior: 'smooth'
      });
    } else {
      container5.scrollTo({
        left: container5.scrollLeft + this.scrollStep,
        behavior: 'smooth'
      });
    }
  }

  scroll6(direction: 'left' | 'right') {
    const container6 = this.cardContainer6.nativeElement as HTMLElement;

    if (direction === 'left') {
      container6.scrollTo({
        left: container6.scrollLeft - this.scrollStep,
        behavior: 'smooth'
      });
    } else {
      container6.scrollTo({
        left: container6.scrollLeft + this.scrollStep,
        behavior: 'smooth'
      });
    }
  }
  getComedyMovies() {
    this.movies.fetchComedyMovies().subscribe(
      response => {
        this.comedyMovies = response;
        this.comedyMovies = this.comedyMovies.results;
      }
    )
  }
  getanimatedMovies() {
    this.movies.fetchAnimationMovies().subscribe(
      response => {
        this.animatedMovies = response;
        this.animatedMovies = this.animatedMovies.results;
      }
    )
  }
  getdocumentaryMovies() {
    this.movies.fetchDocumentaryMovies().subscribe(
      response => {
        this.documentaryMovies = response;
        this.documentaryMovies = this.documentaryMovies.results;
      }
    )
  }
  getscienceFictionMovies() {
    this.movies.fetchScienceFictionMovies().subscribe(
      response => {
        this.scienceFictionMovies = response;
        this.scienceFictionMovies = this.scienceFictionMovies.results;
      }
    )
  }
  getThrillerMovies() {
    this.movies.fetchThrillerMovies().subscribe(
      response => {
        this.thrillerMovies = response;
        this.thrillerMovies = this.thrillerMovies.results;
      }
    )
  }
  
  }
