import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root',
})
export class VisitService {
  endpoint: string = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {}

  getVisits(startDate: String, page: number, size: number){
    return this.http.get<any>(`${this.endpoint}/visits?startDate=${startDate}&page=${page}&size=${size}`);
  }
}
