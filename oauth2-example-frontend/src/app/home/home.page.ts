import { Component, OnInit } from '@angular/core';
import { RefresherCustomEvent } from '@ionic/angular';

import { DataService, Message } from '../services/data.service';
import Todo from '../models/todo.model';
import { TodosService } from '../services/todos.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit{

  public todos: Todo[] = [];

  constructor(private data: DataService, private todosService: TodosService) { }

  ngOnInit(): void {
    this.todosService.getPageOfTodos().subscribe(page => this.todos = page.content);
  }

  refresh(ev: any) {
    setTimeout(() => {
      (ev as RefresherCustomEvent).detail.complete();
    }, 3000);
  }

  getMessages(): Message[] {
    return this.data.getMessages();
  }

}
