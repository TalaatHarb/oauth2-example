import { Injectable } from '@angular/core';
import { AuthConfig, OAuthErrorEvent, OAuthService } from 'angular-oauth2-oidc';
import { BehaviorSubject, from, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const ISSUER_URL = environment.issuerUrl;

export const authCodeFlowConfig: AuthConfig = {
  // Url of the Identity Provider
  issuer: ISSUER_URL,

  // URL of the SPA to redirect the user to after login
  redirectUri: window.location.origin,

  // The SPA's id. The SPA is registerd with this id at the auth-server
  // clientId: 'server.code',
  clientId: 'example-frontend',

  responseType: 'code',

  // set the scope for the permissions the client should request
  // The first four are defined by OIDC.
  // Important: Request offline_access to get a refresh token
  // The api scope is a usecase specific one
  scope: 'openid profile email offline_access',

  showDebugInformation: true,

  requireHttps: false,
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private hasValidTokenSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private oauthService: OAuthService) {
    this.oauthService.configure(authCodeFlowConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
    this.oauthService.setupAutomaticSilentRefresh();

    this.oauthService.events.subscribe(event => {
      console.log(event);
      this.hasValidTokenSubject.next(this.oauthService.hasValidAccessToken());
    });

    window.addEventListener('storage', (event) => {
      if (event.key !== 'access_token' && event.key !== null) {
        return;
      }

      console.warn('Noticed changes to access_token (most likely from another tab), updating isAuthenticated');
      this.hasValidTokenSubject.next(this.oauthService.hasValidAccessToken());

    });
  }

  public login(): void {
    this.oauthService.initCodeFlow();
  }

  public logout(): void {
    this.oauthService.revokeTokenAndLogout();
  }

  public loadUserProfile(): Observable<any> {
    return from(this.oauthService.loadUserProfile());
  }

  public hasValidToken(): Observable<boolean> {
    return this.hasValidTokenSubject;
  }
}
