import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public flag: boolean = false;
  public guard: boolean = false;
  public name: string = "";

  constructor(private http: HttpClient) { }

  baseUrl = "http://localhost:8804/movie/api/v1";
  url = "http://localhost:8081/api/v1/login";


  register(file: FormData):Observable<any> {
    const endpoint = 'http://localhost:8804/movie/api/v1/addUser';
  
    return this.http.post(endpoint, file);
     
  }



loginCheck(data: any) {
  return this.http.post(this.url, data);
}


getuser() {
  let httpHeaders = new HttpHeaders({
    'Authorization': 'Bearer ' + localStorage.getItem('jwtKey')
  });
  let requestOptions = { headers: httpHeaders }
  return this.http.get(this.baseUrl + "/get", requestOptions);
}

getUserByEmail(email: string) {
  return this.http.get(this.baseUrl + "/getBy/" + email);
}

updateuser(recorddata: any) {
  let httpHeaders = new HttpHeaders({
    'Authorization': 'Bearer ' + localStorage.getItem('jwtKey')
  });
  let requestOptions = { headers: httpHeaders }
  return this.http.post(this.baseUrl + "/update", recorddata, requestOptions);
}

updateUserWithEmail(file: File, userEmail: string, userData: any): Observable<any> {
  const formData: FormData = new FormData();
  formData.append('file', file, file.name);
  formData.append('email', userEmail);
  formData.append('userData', JSON.stringify(userData));

  let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwtKey')
  });
  let requestOptions = { headers: httpHeaders };

  return this.http.put(`${this.baseUrl}/updateWithEmail`, formData, requestOptions);
}

resetPassword(email: string, password: string) {
  let httpHeaders = new HttpHeaders({
    'Authorization': 'Bearer ' + localStorage.getItem('jwtKey')
  });
  let requestOptions = { headers: httpHeaders }
  return this.http.post("http://localhost:8804/movie/api/v1/updatePassword/" + email + "/" + password, requestOptions, { responseType: 'text' });
}

}
