import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShowBooksComponent} from './book-list/book-list.component';
import { DeleteBookComponent} from './book-delete/book-delete.component';
import { AddBookComponent } from './book-add/book-add.component';
import { UpdateBookComponent} from './book-update/book-update.component';
const routes: Routes = [
  {path: '', redirectTo: '/showBooks', pathMatch: 'full'},
  {path: 'showBooks', component: ShowBooksComponent},
  {path: 'deleteBook', component: DeleteBookComponent},
  {path: 'addBook', component: AddBookComponent},
  {path: 'updateBook', component: UpdateBookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
