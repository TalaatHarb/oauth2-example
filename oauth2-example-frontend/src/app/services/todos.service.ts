import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Todo from '../models/todo.model';
import { Page } from '../models/page.model';
import { Pageable } from '../models/pageable.model';
import { environment } from 'src/environments/environment';

const API_URL = environment.apiUrl;
const DEFAULT_PAGE_SIZE = environment.defaultPageSize;
const DEFAULT_SORT = environment.defaultSort;
const TODOS_URL = `${API_URL}/api/v1/todos`;

@Injectable({
  providedIn: 'root'
})
export class TodosService {

  constructor(private httpClient: HttpClient) { }

  createTodo(todo: Todo): Observable<Todo> {
    return this.httpClient.post<Todo>(TODOS_URL, todo);
  }

  getPageOfTodos(pageable: Pageable = { pageNumber: 0, pageSize: DEFAULT_PAGE_SIZE, sort: DEFAULT_SORT }): Observable<Page<Todo>> {
    return this.httpClient.get<Page<Todo>>(TODOS_URL,
      { params: { pageNumber: pageable.pageNumber, pageSize: pageable.pageSize } });
  }

  getTodo(id: number): Observable<Todo> {
    return this.httpClient.get<Todo>(`${TODOS_URL}/${id}`);
  }
}
