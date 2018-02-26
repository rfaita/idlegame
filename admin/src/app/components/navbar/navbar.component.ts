import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
import { MailService } from '../../service/mail.service';
import { Mail } from '../../model/mail';
import { showMailNotification } from '../../utils/helper';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    private listTitles: any[];
    location: Location;
    private toggleButton: any;
    private sidebarVisible: boolean;

    public userDetails: KeycloakProfile;

    public mails: Mail[];
    public mail: Mail;

    constructor(location: Location,
        private element: ElementRef,
        private keycloakService: KeycloakService,
        private mailService: MailService) {
        this.location = location;
        this.sidebarVisible = false;
    }

    ngOnInit() {
        this.listTitles = ROUTES.filter(listTitle => listTitle);
        const navbar: HTMLElement = this.element.nativeElement;
        this.toggleButton = navbar.getElementsByClassName('navbar-toggle')[0];

        this.keycloakService.loadUserProfile().then(profile => { this.userDetails = profile });

        let subject = this.keycloakService.getKeycloakInstance().subject;


        this.mailService.findAllOldMail().subscribe(mails => {
            this.mails = mails;
            this.mailService.subscribePrivateMail(subject).subscribe(mail => {
                showMailNotification("New Mail from: " + mail.fromNickName);
                this.mails.push(mail);
            });
        });

        this.mailService.subscribePrivateMailDelete(subject).subscribe(mailDeleted => {
            for (let i = 0; i < this.mails.length; i++) {
                if (this.mails[i].id === mailDeleted.id) {
                    this.mails.splice(i, 1);
                }
            }
        });

        this.mailService.subscribePrivateMailUpdate(subject).subscribe(mailUpdated => {
            for (let i = 0; i < this.mails.length; i++) {
                if (this.mails[i].id === mailUpdated.id) {
                    this.mails[i] = mailUpdated;
                }
            }
        });

    }

    unreadLength(): number {
        return this.mails.filter(mail => !mail.readed).length;
    }


    deletePrivateMail(mail: Mail) {
        this.mailService.deletePrivateMail(mail.id);
    }

    markAsReadPrivateMail(mail: Mail) {
        this.mail = mail;
        if (!this.mail.readed) {
            this.mailService.markAsReadPrivateMail(mail.id);
        }
    }

    logout() {
        this.keycloakService.logout();
    }

    sidebarOpen() {
        const toggleButton = this.toggleButton;
        const body = document.getElementsByTagName('body')[0];
        setTimeout(function () {
            toggleButton.classList.add('toggled');
        }, 500);
        body.classList.add('nav-open');

        this.sidebarVisible = true;
    };
    sidebarClose() {
        const body = document.getElementsByTagName('body')[0];
        this.toggleButton.classList.remove('toggled');
        this.sidebarVisible = false;
        body.classList.remove('nav-open');
    };
    sidebarToggle() {
        // const toggleButton = this.toggleButton;
        // const body = document.getElementsByTagName('body')[0];
        if (this.sidebarVisible === false) {
            this.sidebarOpen();
        } else {
            this.sidebarClose();
        }
    };

    getTitle() {
        var titlee = this.location.prepareExternalUrl(this.location.path());
        if (titlee.charAt(0) === '#') {
            titlee = titlee.slice(2);
        }
        titlee = titlee.split('/').pop();

        for (var item = 0; item < this.listTitles.length; item++) {
            if (this.listTitles[item].path === titlee) {
                return this.listTitles[item].title;
            }
        }
        return '';
    }
}
