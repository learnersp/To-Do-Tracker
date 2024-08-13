import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserAuthenticationService {

  constructor(private httpClient: HttpClient) {}

  url: string = 'http://localhost:9000';
  hideIcon?: boolean;
  generateToken(data: any): Observable<any> {
    
    return this.httpClient.post<any>(this.url + '/api/v2/login', data).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('An error occurred:', error);
        return throwError('Something went wrong. Please try again later.');
      })
    );
  }

  // login(emailId:string,password:string): Observable<any> {
  //   //this.isLoggedIn = true;
  //   const body = { emailId, password };
  //   return this.httpClient.post<any>(this.url+'/api/v2/login',body);
  // }
  //to check that the user is logged in or not !!
  loginUser(token: any) {
    console.log(token,'Not login' )
    localStorage.setItem('token', token);
    return true;
  }

  isLoggedIn() {
    let token = localStorage.getItem('token');
    if (token == undefined || token === '' || token == null) {
      return false;
    } else {
      return true;
    }
  }

  logout() {
    localStorage.removeItem('token');
    return true;
  }

  getToken() {
    return localStorage.getItem('token');
  }
}