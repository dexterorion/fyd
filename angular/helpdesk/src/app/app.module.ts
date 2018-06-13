import { NivelExecucaoService } from './service/nivel-execucao.service';
import { TipoTreinoService } from './service/tipo-treino.service';
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
import { PilarExercicioService } from './service/pilar-exercicio.service';
import { PilarExercicioListComponent } from './component/pilar-exercicio-list/pilar-exercicio-list.component';
import { PilarExercicioNewComponent } from './component/pilar-exercicio-new/pilar-exercicio-new.component';
import { ModalidadeTreinoService } from './service/modalidade-treino.service';
import { ModalidadeTreinoListComponent } from './component/modalidade-treino-list/modalidade-treino-list.component';
import { ModalidadeTreinoNewComponent } from './component/modalidade-treino-new/modalidade-treino-new.component';
import { TipoTreinoListComponent } from './component/tipo-treino-list/tipo-treino-list.component';
import { TipoTreinoNewComponent } from './component/tipo-treino-new/tipo-treino-new.component';
import { NivelExecucaoListComponent } from './component/nivel-execucao-list/nivel-execucao-list.component';
import { NivelExecucaoNewComponent } from './component/nivel-execucao-new/nivel-execucao-new.component';


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
    FuncaoExercicioListComponent,
    PilarExercicioListComponent,
    PilarExercicioNewComponent,
    ModalidadeTreinoListComponent,
    ModalidadeTreinoNewComponent,
    TipoTreinoListComponent,
    TipoTreinoNewComponent,
    NivelExecucaoListComponent,
    NivelExecucaoNewComponent
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
    PilarExercicioService,
    ModalidadeTreinoService,
    TipoTreinoService,
    NivelExecucaoService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
