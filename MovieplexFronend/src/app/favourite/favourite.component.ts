import { Component } from '@angular/core';
import { FavouriteService } from '../service/favourite.service';
import { MoviesService } from '../service/movies.service';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent {
  constructor(private fav: FavouriteService, private getMovie: MoviesService) { this.displayFav();// Add this code in the component where you want to navigate from
  window.scrollTo(0, 0); }
  favList: any = [];
  data:any;
  ngOnInit() {

  }


  getMovieById(id:any){
    this.getMovie.getMovieDetails(id).subscribe(
      response => {
        this.data = response;
      },
      error => {
        alert(`Error`);
      }
    )
  }

  delete(id: any) {
    this.getMovie.getMovieDetails(id).subscribe(
      response => {
        this.data = response;
        this.fav.deleteMovieFromFavList(this.data).subscribe(
          deleteResponse => {
            alert(`Movie deleted`);
            this.displayFav();
          }          
        );
      },
      error => {
        alert('Error fetching movie details');
      }
    );
  }
  
  displayFav() {
    this.fav.getAllFavMovies().subscribe(
      response => {
        this.favList = response;
      },
      error => {
        alert(`hello`);
      }
    )
  }
}
