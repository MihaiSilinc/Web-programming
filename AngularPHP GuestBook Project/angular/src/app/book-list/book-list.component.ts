import {Component, OnInit} from '@angular/core';
import {GenericService} from '../books-service';
import {Book} from '../book';
import {Router} from '@angular/router';

@Component({
  selector: 'app-show-books',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class ShowBooksComponent implements OnInit {

  books: Array<Book> | undefined;

  constructor(private service: GenericService, private router: Router) {
  }

  ngOnInit(): void {
    this.refresh('', '');
  }

  refresh(author: string, title: string, comment: string: void {
    this.service.fetchBooks(author, title, comment).subscribe(books => this.books = books);
  }

  navigateToDelete(bookId: number): void {
    this.router.navigate(['deleteBook'], {queryParams: {id: bookId}}).then(_ => {
    });
  }

  navigateToAdd(): void {
    this.router.navigate(['addBook']).then(_ => {
    });
  }

  navigateToUpdate(bookId: number): void {
    this.router.navigate(['updateBook'], {queryParams: {id: bookId}}).then(_ => {
    });
  }

}
