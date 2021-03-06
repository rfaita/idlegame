import { Component, OnInit } from '@angular/core';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: 'shop', title: 'Shop', icon: 'games', class: '' },
    { path: 'inventory', title: 'Inventory', icon: 'games', class: '' },
    { path: 'guild', title: 'Guild', icon: 'supervisor_account', class: '' },
    { path: 'friends', title: 'Friends', icon: 'supervisor_account', class: '' },
    { path: 'pvp', title: 'Pvp', icon: 'games', class: '' },
    { path: 'chat', title: 'Chat', icon: 'chat', class: '' },
    { path: 'battle', title: 'Battle Simulator', icon: 'content_paste', class: '' },
    { path: 'heroes', title: 'Heroes', icon: 'content_paste', class: '' },
    { path: 'heroTypes', title: 'Heroes Type', icon: 'content_paste', class: '' }
];

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
    menuItems: any[];

    constructor() { }

    ngOnInit() {
        this.menuItems = ROUTES.filter(menuItem => menuItem);
    }
    isMobileMenu() {
        if ($(window).width() > 991) {
            return false;
        }
        return true;
    };
}
