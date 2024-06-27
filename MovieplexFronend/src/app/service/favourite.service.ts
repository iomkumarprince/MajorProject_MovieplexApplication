import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {
  
  public email: string = "";

  constructor(private http: HttpClient) { }
  baseUrl = "http://localhost:8804/movie/api/v1";

 
  getProfileImg(){
    // const uEmail = localStorage.getItem('email');
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem('jwtKey')
    });
    let reqOption  = {headers:httpHeader}
    console.log(reqOption);

    // return this.httpClient.get(`${this.userUrl}/get/profile?email=${uEmail}`);
    return this.http.get(`${this.baseUrl}/get/profile`,reqOption);
  }

  addMovieToFavList(data: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwtKey')
    });
    let requestOptions = { headers: httpHeaders }
    return this.http.post(this.baseUrl + "/addMovie", data, requestOptions);
  }

  deleteMovieFromFavList(data: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwtKey')
    });
    let requestOptions = { headers: httpHeaders }
    return this.http.post(this.baseUrl + "/deleteMovie", data, requestOptions);
  }

  getAllFavMovies() {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwtKey')
    });
    let requestOptions = { headers: httpHeaders }
    return this.http.get(this.baseUrl + "/getFavouriteMovies", requestOptions);
  }
}
