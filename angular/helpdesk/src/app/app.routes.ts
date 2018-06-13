import { NivelExecucaoNewComponent } from './component/nivel-execucao-new/nivel-execucao-new.component';
import { NivelExecucaoListComponent } from './component/nivel-execucao-list/nivel-execucao-list.component';
import { TipoTreinoListComponent } from './component/tipo-treino-list/tipo-treino-list.component';
import { TipoTreinoNewComponent } from './component/tipo-treino-new/tipo-treino-new.component';
import { ModalidadeTreinoNewComponent } from './component/modalidade-treino-new/modalidade-treino-new.component';
import { ModalidadeTreinoListComponent } from './component/modalidade-treino-list/modalidade-treino-list.component';
import { PilarExercicioNewComponent } from './component/pilar-exercicio-new/pilar-exercicio-new.component';
import { PilarExercicioListComponent } from './component/pilar-exercicio-list/pilar-exercicio-list.component';
import { FuncaoExercicioNewComponent } from './component/funcao-exercicio-new/funcao-exercicio-new.component';
import { UserListComponent } from './component/user-list/user-list.component';
import { AuthGuard } from './component/security/auth.guard';
import { HomeComponent } from './component/home/home.component';
import { Routes,RouterModule } from '@angular/router';
import { LoginComponent } from './component/security/login/login.component';
import { ModuleWithProviders } from '@angular/core';
import { UserNewComponent } from './component/user-new/user-new.component';
import { FuncaoExercicioListComponent } from './component/funcao-exercicio-list/funcao-exercicio-list.component';

export const ROUTES: Routes = [
    { path : '', component: HomeComponent, canActivate: [AuthGuard]},
    { path : 'login', component: LoginComponent},
    { path : 'user/new', component: UserNewComponent, canActivate: [AuthGuard]},
    { path : 'user/new/:id', component: UserNewComponent, canActivate: [AuthGuard]},
    { path : 'user', component: UserListComponent, canActivate: [AuthGuard]},
    { path : 'funcao-exercicio', component: FuncaoExercicioListComponent, canActivate: [AuthGuard]},
    { path : 'funcao-exercicio/new', component: FuncaoExercicioNewComponent, canActivate: [AuthGuard]},
    { path : 'funcao-exercicio/new/:id', component: FuncaoExercicioNewComponent, canActivate: [AuthGuard]},
    { path : 'pilar-exercicio', component: PilarExercicioListComponent, canActivate: [AuthGuard]},
    { path : 'pilar-exercicio/new', component: PilarExercicioNewComponent, canActivate: [AuthGuard]},
    { path : 'pilar-exercicio/new/:id', component: PilarExercicioNewComponent, canActivate: [AuthGuard]},
    { path : 'modalidade-treino', component: ModalidadeTreinoListComponent, canActivate: [AuthGuard]},
    { path : 'modalidade-treino/new', component: ModalidadeTreinoNewComponent, canActivate: [AuthGuard]},
    { path : 'modalidade-treino/new/:id', component: ModalidadeTreinoNewComponent, canActivate: [AuthGuard]},
    { path : 'tipo-treino', component: TipoTreinoListComponent, canActivate: [AuthGuard]},
    { path : 'tipo-treino/new', component: TipoTreinoNewComponent, canActivate: [AuthGuard]},
    { path : 'tipo-treino/new/:id', component: TipoTreinoNewComponent, canActivate: [AuthGuard]},
    { path : 'nivel-execucao', component: NivelExecucaoListComponent, canActivate: [AuthGuard]},
    { path : 'nivel-execucao/new', component: NivelExecucaoNewComponent, canActivate: [AuthGuard]},
    { path : 'nivel-execucao/new/:id', component: NivelExecucaoNewComponent, canActivate: [AuthGuard]}
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);