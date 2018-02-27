import { SnotifyToastConfig, SnotifyPosition } from 'ng-snotify';
export function clone(obj: any) {
    if (obj == null) {
        return null;
    }
    return JSON.parse(JSON.stringify(obj));
}
export function notificationConfig(): SnotifyToastConfig {

    return {
        bodyMaxLength: 100,
        titleMaxLength: 15,
        backdrop: -1,
        position: SnotifyPosition.rightTop,
        timeout: 4000,
        showProgressBar: true,
        closeOnClick: true,
        pauseOnHover: true
    };
}