import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerComponent} from "./customer/customer.component";
import {ProductComponent} from "./product/product.component";
import {AuthGuard} from "./guards/auth.guard";

const routes: Routes = [
  {path:"customers",component:CustomerComponent},
  {path:"products",component:ProductComponent,canActivate:[AuthGuard],data:{roles:['USER']}},
  {path:"products",component:ProductComponent,canActivate:[AuthGuard],data:{roles:['ADMIN']}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
