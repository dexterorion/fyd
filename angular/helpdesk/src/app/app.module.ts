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
import { FuncaoExercicioNewComponent } from './component/funcao-exercicio-new/funcao-exercicio-new.component';
import { FuncaoExercicioListComponent } from './component/funcao-exercicio-list/funcao-exercicio-list.component';
import { FuncaoExercicioService } from './service/funcao-exercicio.service';


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
    FuncaoExercicioNewComponent,
    FuncaoExercicioListComponent
  ],
  imports: [
    BrowserModule,routes, HttpClientModule, FormsModule
  ],
  providers: [
    UserService,
    SharedService,
    DialogService,
    AuthGuard,
    FuncaoExercicioService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
