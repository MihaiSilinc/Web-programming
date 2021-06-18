import {Component, OnInit} from '@angular/core';

import {GenericService} from '../books-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-book-add',
  templateUrl: './book-add.component.html',
  styleUrls: ['./book-add.component.css']
})
export class AddBookComponent implements OnInit {

  constructor(private service: GenericService, private router: Router) {
  }

  ngOnInit(): void {
  }

  addBook(authorOf: string, titleOf: string, commentOf: string, postedOn: string): void {
    this.service.addBook(authorOf, titleOf, commentOf, postedOn).subscribe(() => {
      this.router.navigate(['showBooks']).then(_ => {
      });
    });
  }

  onCancel(): void {
    this.router.navigate(['showBooks']).then(_ => {
    });
  }

}
