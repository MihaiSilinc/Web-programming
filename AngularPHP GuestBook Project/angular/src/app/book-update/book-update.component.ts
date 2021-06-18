import { Component, OnInit } from '@angular/core';
import {GenericService} from '../books-service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-update-book',
  templateUrl: './book-update.component.html',
  styleUrls: ['./book-update.component.css']
})
export class UpdateBookComponent implements OnInit {

  constructor(private service: GenericService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  updateBook(authorOf: string, titleOf: string, commentOf: string, postedOn: string): void {
    const id = this.route.snapshot.queryParams.id;
    this.service.updateBook(id, authorOf, titleOf, commentOf, postedOn).subscribe(() => {
      this.router.navigate(['showBooks']).then(_ => {
      });
    });
  }

  onCancel(): void {
    this.router.navigate(['showBooks']).then(_ => {
    });
  }

}
