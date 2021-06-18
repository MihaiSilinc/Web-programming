import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ShowBooksComponent} from './book-list/book-list.component';
import { DeleteBookComponent} from './book-delete/book-delete.component';
import { AddBookComponent } from './book-add/book-add.component';
import { UpdateBookComponent} from './book-update/book-update.component';

@NgModule({
  declarations: [
    AppComponent,
    ShowBooksComponent,
    DeleteBookComponent,
    AddBookComponent,
    UpdateBookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
