import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {Product} from "../../model/product";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";


/**
 * @title Product Card with multiple sections
 */
@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css'],
  standalone: true,
  imports: [MatCardModule, MatButtonModule, MatIconModule],
})
export class ProductCardComponent {
  @Input() fullWidthMode = false;
  @Input() product: Product | undefined;
  @Output() addToCart = new EventEmitter();

  constructor() {
  }

  onAddToCart(): void {
    this.addToCart.emit(this.product);
  }

}
