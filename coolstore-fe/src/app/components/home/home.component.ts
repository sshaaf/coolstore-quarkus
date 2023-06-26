import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import {Product} from "../../model/product.model";
import {CatalogService} from "../../services/catalog.service";
import {CartService} from "../../services/cart.service";


const ROWS_HEIGHT: { [id: number]: number } = { 1: 400, 3: 335, 4: 350 };

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  constructor(
    private catalogService: CatalogService,
    private cartService: CartService
  ) {}

  cols = 3;
  rowHeight: number = ROWS_HEIGHT[this.cols];
  products: Array<Product> | undefined;
  productsSubscription: Subscription | undefined;

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.productsSubscription = this.catalogService.getAllProducts().subscribe(
      {
        next: (data) => {
          this.products = data;
        },
        error: (e) => console.error(e)
      }
    );
  }

  onAddToCart(product: Product): void {
    this.cartService.addToCart({
      product: product.image,
      name: product.title,
      price: product.price,
      quantity: 1,
      itemId: product.itemId,
    });
  }


  ngOnDestroy(): void {
    if (this.productsSubscription) {
      this.productsSubscription.unsubscribe();
    }
  }

}
