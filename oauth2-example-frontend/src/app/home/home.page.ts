import { Component, OnInit, ViewChild } from '@angular/core';
import { IonModal, RefresherCustomEvent } from '@ionic/angular';
import { OverlayEventDetail } from '@ionic/core/components';


import Todo from '../models/todo.model';
import { TodosService } from '../services/todos.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit{

  public todos: Todo[] = [];
  public name = '';

  @ViewChild(IonModal)
  modal!: IonModal;

  constructor(private todosService: TodosService) { }

  ngOnInit(): void {
    this.todosService.getPageOfTodos().subscribe(page => this.todos = page.content);
  }

  refresh(ev: any) {
    setTimeout(() => {
      (ev as RefresherCustomEvent).detail.complete();
    }, 3000);
  }

  public cancel(): void {
    this.modal.dismiss(null, 'cancel');
  }

  public confirm(): void {
    this.modal.dismiss(this.name, 'confirm');
  }

  public onWillDismiss(event: Event): void {
    this.name = '';
    const ev = event as CustomEvent<OverlayEventDetail<string>>;
    if (ev.detail.role === 'confirm') {
      const todoName = ev.detail.data;
      const todo: Todo = {title: todoName as string};
      this.todosService.createTodo(todo).subscribe((t) => {
        this.todos.push(t);
      });
    }
  }

}
