import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Book} from './book';


@Injectable({
  providedIn: 'root'
})
export class GenericService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  private backendUrl = 'http://localhost/';

  constructor(private http: HttpClient) {
  }

  fetchBooks(author: string, title: string, comment: string): Observable<Book[]> {
    /* body of the method */
    return this.http.get<Book[]>(this.backendUrl + `showBooks.php?author=${author}&title=${title}`)
      .pipe(catchError(this.handleError<Book[]>('fetchBooks', []))
      );
  }

  deleteBook(bookId: number): Observable<any> {
    return this.http.post(this.backendUrl + `deleteBook.php`, {id: bookId});
  }

  addBook(authorOf: string, titleOf: string, commentOf: string, postedOn: string): Observable<any> {
    return this.http.post(this.backendUrl + `addBook.php`, {
      author: authorOf,
      title: titleOf,
      comment: commentOf,
      posted_on: postedOn,

    });
  }

  updateBook(idOf: number, authorOf: string, titleOf: string, commentOf: string, postedOn: string): Observable<any> {
    return this.http.post(this.backendUrl + `updateBook.php`, {
      id: idOf,
      author: authorOf,
      title: titleOf,
      comment: commentOf,
      posted_on: postedOn
    });
  }

  private handleError<T>(operation = 'operation', result?: T): (error: any) => Observable<T> {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }
}
