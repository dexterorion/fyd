import { TicketService } from './service/ticket.service';
import { DialogService } from './dialog.service';
import { AuthGuard } from './component/security/auth.guard';
import { AuthInterceptor } from './component/security/auth.interceptor';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SharedService } from './service/shared.service';
import { UserService } from './service/user.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { MenuComponent } from './component/menu/menu.component';
import { FooterComponent } from './component/footer/footer.component';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/security/login/login.component';
import { routes } from './app.routes';
import { UserNewComponent } from './component/user-new/user-new.component';
import { UserListComponent } from './component/user-list/user-list.component';
import { TicketNewComponent } from './component/ticket-new/ticket-new.component';
import { TicketListComponent } from './component/ticket-list/ticket-list.component';
import { TicketDetailComponent } from './component/ticket-detail/ticket-detail.component';
import { SummaryComponent } from './component/summary/summary.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MenuComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    UserNewComponent,
    UserListComponent,
    TicketNewComponent,
    TicketListComponent,
    TicketDetailComponent,
    SummaryComponent
  ],
  imports: [
    BrowserModule,routes, HttpClientModule, FormsModule
  ],
  providers: [UserService, SharedService, DialogService, AuthGuard, TicketService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
