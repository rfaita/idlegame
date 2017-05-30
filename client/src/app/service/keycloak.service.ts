import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';

declare var Keycloak: any;

@Injectable()
export class KeycloakService {
    static auth: any = {};

    static init(): Promise<any> {
        const keycloakAuth: any = Keycloak({
            url: environment.AUTH_URL,
            realm: environment.AUTH_REALM,
            clientId: environment.AUTH_CLIENT
        });

        KeycloakService.auth.loggedIn = false;

        return new Promise((resolve, reject) => {
            keycloakAuth.init({ onLoad: 'login-required' })
                .success(() => {
                    KeycloakService.auth.loggedIn = true;
                    KeycloakService.auth.authz = keycloakAuth;
                    KeycloakService.auth.logoutUrl = keycloakAuth.authServerUrl
                        + '/realms/' + environment.AUTH_REALM + '/protocol/openid-connect/logout?redirect_uri='
                        + document.baseURI;
                    resolve();
                })
                .error(() => {
                    reject();
                });
        });
    }

    logout() {
        KeycloakService.auth.loggedIn = false;
        KeycloakService.auth.authz = null;

        window.location.href = KeycloakService.auth.logoutUrl;
    }

    getToken(): Promise<string> {
        return new Promise<string>((resolve, reject) => {
            if (KeycloakService.auth.authz.token) {
                KeycloakService.auth.authz
                    .updateToken(5)
                    .success(() => {
                        resolve(<string>KeycloakService.auth.authz.token);
                    })
                    .error(() => {
                        reject('Failed to refresh token');
                    });
            } else {
                reject('Not loggen in');
            }
        });
    }
}