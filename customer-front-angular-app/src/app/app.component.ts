import {Component, OnInit} from '@angular/core';
import {SecurityService} from "./services/secutity.service";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'customer-front-angular-app';
  profile:any

  ngOnInit(): void {
    if(this.keyclock.isLoggedIn())this.keyclock.loadUserProfile().then(value =>
      this.profile=value)

  }
  constructor(public keyclock:KeycloakService) {
  }
  async login(){
    await this.keyclock.login({
      redirectUri:window.location.origin
    })
  }
  logout (){
    this.keyclock.logout(window.location.origin)
  }
}
