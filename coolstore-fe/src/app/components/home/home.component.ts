import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import {Product} from "../../model/product";
import {CatalogService} from "../../services/catalog.service";


const ROWS_HEIGHT: { [id: number]: number } = { 1: 400, 3: 335, 4: 350 };

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit, OnDestroy {



  constructor(
    private catalogService: CatalogService
  ) {}

  cols = 3;
  rowHeight: number = ROWS_HEIGHT[this.cols];
  products?: Product[];
  count = '12';
  sort = 'desc';
  category: string | undefined;
  productsSubscription: Subscription | undefined;

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.catalogService.getAllProducts().subscribe(
      {
        next: (data) => {
          this.products = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      }
    );
  }

  ngOnDestroy(): void {
    if (this.productsSubscription) {
      this.productsSubscription.unsubscribe();
    }
  }

}
