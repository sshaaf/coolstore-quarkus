import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CartComponent} from "./components/cart/cart.component";
import {AppComponent} from "./app.component";
import {DashComponent} from "./components/dash/dash.component";

const routes: Routes = [
  { path: '', component: AppComponent,  },
  { path: 'cart', component: CartComponent, },
  { path: 'dash', component: DashComponent, },
  { path: '**', component: AppComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
