import {Component, OnInit} from '@angular/core';
import {GenericService} from '../books-service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-book-delete',
  templateUrl: './book-delete.component.html',
  styleUrls: ['./book-delete.component.css']
})
export class DeleteBookComponent implements OnInit {

  constructor(private service: GenericService, private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
  }

  onYes(): void {
    this.service.deleteBook(this.route.snapshot.queryParams.id).subscribe(() => {
      this.router.navigate(['showBooks']).then(_ => {
      });
    });
  }

  onNo(): void {
    this.router.navigate(['showBooks']).then(_ => {
    });
  }

}
