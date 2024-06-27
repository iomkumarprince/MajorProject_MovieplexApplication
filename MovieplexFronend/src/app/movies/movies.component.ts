import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FavouriteService } from '../service/favourite.service';
import { MoviesService } from '../service/movies.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent {
  constructor(private get: MoviesService, private route: ActivatedRoute, private fav: FavouriteService) { 
    // Add this code in the component where you want to navigate from
    window.scrollTo(0, 0);

  }
  itemList: any = [];
  video: any = [];
  clip: any = [];
  storeResponse: any = [];
  showDropdown:boolean = false;
  message:string = "Movie Added Successfully";
  
  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    console.log(id, 'getparamid#');
    this.get1(id)
    this.getvideo(id)
    this.getcast(id)
    this.getdetail(id)
  }
  show() {
    this.itemList = this.get.item;
  }

  getvideo(id: any) {

    this.get.getMovieVideo(id).subscribe(
      response => {
        this.video = response;
        this.storeResponse = this.video;
        this.videos(this.video);

      });

  };
  get1(id: any) {
    this.get.getMovieDetails(id).subscribe(data => {
      this.itemList = data
      console.log(data)
    })
  }

  videos(data: any) {
    data.results.forEach((element: any) => {
      if (element.type == "Trailer") {
        this.video = element.key;
      }
      else {
        this.clip = element.key;
      }
    })
  }
  clips(data: any) {
    data.results.forEach((element: any) => {
      if (element.type == "Clip") {
        this.clip = element.key;
      }
    })
  }
  cast: any[] = [];
  getcast(id: any) {
    this.get.getMovieCast(id).subscribe(data => {
      this.cast = data.cast.slice(0, 8);
    })
  }

  @Input() selected: boolean | undefined;
  @Output() selectedChange = new EventEmitter<boolean>();

  filteredData: any;
  list: any = []
  getdetail(id: any) {
    this.fav.getAllFavMovies().subscribe(
      (response: any) => {
        response.forEach((element: any) => {

          if (element.id == id) {
            this.selected = !this.selected;
          }
          else {
            this.selected = this.selected;
          }
        })

      }
    )
  }
  public toggleSelected() {

    this.selected = !this.selected;
    this.selectedChange.emit(this.selected);


    if (this.selected) {
      this.fav.addMovieToFavList(this.itemList).subscribe(
        response => {
          this.showSnackbar(this.message);
          this.list = response;
        },
        error => {
          alert(`This Movie is Already marked as Favourite!`);
        }
      )
    } else {
      this.fav.deleteMovieFromFavList(this.itemList).subscribe(
        response => {
          this.showSnackbar("Movie unmarked as Favourite");
        },
        error => {
          alert(`Something went Wrong!`);
        }
      );
    }
  }

  delete(){

  }

  
  showSnackbar(message: string): void {
    const snackbar = document.getElementById("snackbar");
    if (snackbar) {
      snackbar.textContent = message;
      snackbar.classList.add("show");
      setTimeout(() => {
        snackbar.classList.remove("show");
      }, 4000); // Snackbar will be visible for 3 seconds (3000 milliseconds)
    }
  }
}
