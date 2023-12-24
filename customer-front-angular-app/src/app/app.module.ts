import {APP_INITIALIZER, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import {HttpClientModule} from "@angular/common/http";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {ProductComponent} from "./product/product.component";
import {CustomerComponent} from "./customer/customer.component";
import {SecurityService} from "./services/secutity.service";

function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:8080',
        realm: 'sdia-realm',
        clientId: 'angular-client'
      },
      initOptions: {
        onLoad: 'check-sso',
        checkLoginIframe : true,
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    });
}

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    CustomerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    KeycloakAngularModule
  ],
  providers: [
    {provide : APP_INITIALIZER, deps : [KeycloakService],useFactory : initializeKeycloak, multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
