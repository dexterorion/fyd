import { SummaryComponent } from './component/summary/summary.component';
import { TicketDetailComponent } from './component/ticket-detail/ticket-detail.component';
import { TicketListComponent } from './component/ticket-list/ticket-list.component';
import { TicketNewComponent } from './component/ticket-new/ticket-new.component';
import { UserListComponent } from './component/user-list/user-list.component';
import { AuthGuard } from './component/security/auth.guard';
import { HomeComponent } from './component/home/home.component';
import { Routes,RouterModule } from '@angular/router';
import { LoginComponent } from './component/security/login/login.component';
import { ModuleWithProviders } from '@angular/core';
import { UserNewComponent } from './component/user-new/user-new.component';

export const ROUTES: Routes = [
    { path : '', component: HomeComponent, canActivate: [AuthGuard]},
    { path : 'login', component: LoginComponent},
    { path : 'user-new', component: UserNewComponent, canActivate: [AuthGuard]},
    { path : 'user-new/:id', component: UserNewComponent, canActivate: [AuthGuard]},
    { path : 'user-list', component: UserListComponent, canActivate: [AuthGuard]},
    { path : 'ticket-new', component: TicketNewComponent, canActivate: [AuthGuard]},
    { path : 'ticket-new/:id', component: TicketNewComponent, canActivate: [AuthGuard]},
    { path : 'ticket-detail/:id', component: TicketDetailComponent, canActivate: [AuthGuard]},
    { path : 'ticket-list', component: TicketListComponent, canActivate: [AuthGuard]},
    { path : 'summary', component: SummaryComponent, canActivate: [AuthGuard]}
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);