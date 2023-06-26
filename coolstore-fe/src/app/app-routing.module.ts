import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CartComponent} from "./components/cart/cart.component";
import {HomeComponent} from "./components/home/home.component";
import {DashComponent} from "./components/dash/dash.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent, },
  { path: 'cart', component: CartComponent, },
  { path: 'dashboard', component: DashComponent, },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
