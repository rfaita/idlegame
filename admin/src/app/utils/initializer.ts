import { KeycloakService } from 'keycloak-angular';
import { environment } from '../../environments/environment';

export function initializer(keycloak: KeycloakService): () => Promise<any> {
    return (): Promise<any> => {
        return new Promise(async (resolve, reject) => {
            try {
                try {
                    await keycloak.init({
                        config: {
                            url: environment.AUTH_URL,
                            realm: environment.AUTH_REALM,
                            clientId: environment.AUTH_CLIENT
                        },
                        initOptions: {
                            onLoad: 'login-required',
                            checkLoginIframe: false
                        },
                        bearerExcludedUrls: [
                            '/assets',
                            '/clients/public'
                        ],
                    });
                    resolve();
                } catch (error) { }
                
            } catch (error) {
                reject(error);
            }
        });
    };
}