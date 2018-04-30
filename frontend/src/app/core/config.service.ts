// import { Injectable } from '@angular/core';
// import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
// import { HttpErrorResponse, HttpClient } from '@angular/common/http';

// @Injectable()
// export class Config {

// constructor(private http: HttpClient) { }

// private handleError(error: HttpErrorResponse) {
//   if (error.error instanceof ErrorEvent) {
//     // A client-side or network error occurred. Handle it accordingly.
//     console.error('An error occurred:', error.error.message);
//   } else {
//     // The backend returned an unsuccessful response code.
//     // The response body may contain clues as to what went wrong,
//     console.error(
//       `Backend returned code ${error.status}, ` +
//       `body was: ${error.error}`);
//   }
//   // return an ErrorObservable with a user-facing error message
//   return new ErrorObservable(
//     'Something bad happened; please try again later.');
// }

// getConfig() {
//   return this.http.get<Config>(this.configUrl)
//     .pipe(
//       catchError(this.handleError)
//     );
// }

// }
