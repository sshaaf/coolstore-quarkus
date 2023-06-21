import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CartComponent} from "./components/cart/cart.component";
import {AppComponent} from "./app.component";

const routes: Routes = [
  {
    path: 'home',
    component: AppComponent,
  },
  {
    path: 'cart',
    component: CartComponent,
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
