
import { Injectable } from '@angular/core';
import { v4 as uuidv4 } from 'uuid';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LocalService {


  private CART_ID_KEY: string;

  constructor() {
    this.CART_ID_KEY = environment.CART_ID_KEY;
  }

  public getCreateCartId(){
    this.saveData(this.CART_ID_KEY, uuidv4());
    return this.getData(this.CART_ID_KEY);
  }

  public getCartId(){
    return this.getData(this.CART_ID_KEY);
  }

  public saveData(key: string, value: string) {
    localStorage.setItem(key, value);
  }
  public getData(key: string) {
    return localStorage.getItem(key)
  }
  public removeData(key: string) {
    localStorage.removeItem(key);
  }
  public clearData() {
    localStorage.clear();
  }
}
