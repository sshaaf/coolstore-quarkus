import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Cart, CartItem } from '../model/cart.model';
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { LocalService } from "./local.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class CartService {
  cart = new BehaviorSubject<Cart>({ items: [] });

  private readonly apiUrl: string;

  private store: LocalService;

  constructor(private _snackBar: MatSnackBar, private httpClient: HttpClient, private localService:LocalService) {
    this.apiUrl = environment.cartApiUrl+"/cart/v2";
    console.log("Cart API -> "+this.apiUrl)
    this.store = localService;

  }

  setCart(){
    if(this.store.getCartId() == null)
    { // @ts-ignore
      this.cartId = this.store.getCreateCartId().toString();
    }

    this._getCart(this.store.getCartId()).subscribe(
      {
        next: (data) => {
          console.log(data);
        },
        error: (e) => console.error(e)
      }
    );
    console.log("cart id initialized "+this.store.getCartId());
  }

  addToCart(item: CartItem): void {
    const items = [...this.cart.value.items];
    const itemInCart = items.find((_item) => _item.itemId === item.itemId);
    if (itemInCart) {
      itemInCart.quantity += 1;
    } else {
      items.push(item);
    }

    this.cart.next({ items });
    this._putCartItem(this.store.getCartId(), item).subscribe(
      {
        next: (data) => {
          console.log(data);
        },
        error: (e) => console.error(e)
      }
    );
    this._snackBar.open('1 item added to cart.', 'Ok', { duration: 3000 });
  }

  removeFromCart(item: CartItem, updateCart = true): CartItem[] {
    const filteredItems = this.cart.value.items.filter(
      (_item) => _item.itemId !== item.itemId
    );

    if (updateCart) {
      this.cart.next({ items: filteredItems });
      this._snackBar.open('1 item removed from cart.', 'Ok', {
        duration: 3000,
      });
    }

    return filteredItems;
  }

  removeQuantity(item: CartItem): void {
    let itemForRemoval!: CartItem;

    let filteredItems = this.cart.value.items.map((_item) => {
      if (_item.itemId === item.itemId) {
        _item.quantity--;
        if (_item.quantity === 0) {
          itemForRemoval = _item;
        }
      }

      return _item;
    });

    if (itemForRemoval) {
      filteredItems = this.removeFromCart(itemForRemoval, false);
    }

    this.cart.next({ items: filteredItems });
    this._snackBar.open('1 item removed from cart.', 'Ok', {
      duration: 3000,
    });
  }

  clearCart(): void {
    this.cart.next({ items: [] });
    this._snackBar.open('Cart is cleared.', 'Ok', {
      duration: 3000,
    });
    this._checkoutCart(this.store.getCartId()).subscribe(
      {
        next: (data) => {
          console.log(data);
        },
        error: (e) => console.error(e)
      }
    );
  }

  getTotal(items: CartItem[]): number {
    return items
      .map((item) => item.price * item.quantity)
      .reduce((prev, current) => prev + current, 0);
  }

  _getCart(cartId: any): Observable<any> {
    return this.httpClient.get<CartItem[]>(`${this.apiUrl}/${cartId}`)
  }

  _putCartItem(cartId: any,  data: any): Observable<any> {
    return this.httpClient.put(`${this.apiUrl}/${cartId}`, data);
  }

  _deleteCartItem(cartId: any,  data: any): Observable<any> {
    return this.httpClient.delete(`${this.apiUrl}/${cartId}`, data);
  }

  _checkoutCart(cartId: any): Observable<any> {
    return this.httpClient.post<CartItem[]>(`${this.apiUrl}/${cartId}`,null);
  }

}


