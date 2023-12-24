import {Injectable} from "@angular/core";
import {KeycloakProfile} from "keycloak-js";
import {KeycloakEventType, KeycloakService} from "keycloak-angular";

//@Injectable({providedIn : "root"})
export class SecurityService {
  public profile? : KeycloakProfile;
  constructor (public kcService: KeycloakService) {
    this.init();
  }
  init(){
    console.log("Init ....")
    this.kcService.keycloakEvents$.subscribe( e =>  {
        console.log(e);
        if (e.type == KeycloakEventType.OnAuthSuccess) {
          console.log("OnAuthSuccess")
          this.kcService.loadUserProfile().then(profile=>{
            console.log(profile);
            this.profile=profile;
          },reason => console.log(reason));
        }
      }
     );
  }
  public hasRoleIn(roles:string[]):boolean{
    let userRoles = this.kcService.getUserRoles();
    for(let role of roles){
      if (userRoles.includes(role)) return true;
    } return false;
  }
}
