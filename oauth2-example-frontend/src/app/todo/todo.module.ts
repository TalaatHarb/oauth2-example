import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TodoComponent } from './todo.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';



@NgModule({
  declarations: [TodoComponent],
  exports: [TodoComponent],
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule,

  ]
})
export class TodoComponentModule { }
