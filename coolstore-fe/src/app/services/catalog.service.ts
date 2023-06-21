import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../model/product.model';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  private apiUrl: string;

  constructor(private httpClient: HttpClient) {
    this.apiUrl = environment.catalogApiUrl+"/products";
    console.log("API -> "+this.apiUrl)
  }


  getAllProducts(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.apiUrl);
  }

}
