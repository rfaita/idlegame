import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';

import { AvatarModule } from 'ngx-avatar';
import { NumberPipe } from '../utils/pipe/number.pipe';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    AvatarModule
  ],
  declarations: [
    NavbarComponent,
    SidebarComponent,
    NumberPipe
  ],
  exports: [
    NavbarComponent,
    SidebarComponent
  ]
})
export class ComponentsModule { }
