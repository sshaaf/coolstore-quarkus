import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {Product} from "../../model/product.model";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {AsyncPipe, NgForOf} from "@angular/common";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatMenuModule} from "@angular/material/menu";


/**
 * @title Product Card with multiple sections
 */
@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css'],
  standalone: true,
  imports: [MatCardModule, MatButtonModule, MatIconModule, AsyncPipe, MatGridListModule, MatMenuModule, NgForOf],
})
export class ProductCardComponent {
  @Input() fullWidthMode = false;
  @Input() product: Product | undefined;
  @Output() addToCart = new EventEmitter();

  constructor() {
  }

  onAddToCart(): void {
    console.log("clicked add to cart..")
    this.addToCart.emit(this.product);
  }

}
