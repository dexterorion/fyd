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
    { path : 'funcao-exercicio/new/:id', component: FuncaoExercicioNewComponent, canActivate: [AuthGuard]}
]

export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);