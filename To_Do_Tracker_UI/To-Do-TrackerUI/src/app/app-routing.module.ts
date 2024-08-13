import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ViewArchiveTaskComponent } from './view-archive-task/view-archive-task.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ViewTasksComponent } from './view-tasks/view-tasks.component';
import { NotificationComponent } from './notification/notification.component';
import { HeaderSidenavbarComponent } from './header-sidenavbar/header-sidenavbar.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SendConfirmationComponent } from './filtered-task/send-confirmation/send-confirmation.component';
import { ViewPersonalTasksComponent } from './filtered-task/view-personal-tasks/view-personal-tasks.component';
import { ViewTodayTasksComponent } from './filtered-task/view-today-tasks/view-today-tasks.component';
import { ViewUpcomingTasksComponent } from './filtered-task/view-upcoming-tasks/view-upcoming-tasks.component';
import { ViewWorkTasksComponent } from './filtered-task/view-work-tasks/view-work-tasks.component';
import { SettingsComponent } from './settings/settings.component';
import { HelpcenterComponent } from './helpcenter/helpcenter.component';
import { ViewCompletedTaskComponent } from './filtered-task/view-completed-task/view-completed-task.component';
import { AuthGuard } from './guard/auth.guard';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  
  {
    path:"",
    component:HomeComponent
  },


  {
    path:"login",
    component:LoginComponent
  },
  
  {
    path:"home",
    component:HomeComponent
  },
  
  {
    path:"register",
    component:RegisterComponent
  },
  {
    path:"view-archive-task",
    component:ViewArchiveTaskComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"view-personal-tasks",
    component:ViewPersonalTasksComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"view-today-tasks",
    component:ViewTodayTasksComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"view-upcoming-tasks",
    component:ViewUpcomingTasksComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"view-work-tasks",
    component:ViewWorkTasksComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"settings",
    component:SettingsComponent
  },
  
  {
    path:"view-complete-tasks",
    component:ViewCompletedTaskComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"user-profile",
    component:UserProfileComponent,
    // canActivate:[AuthGuard]
  },
  {
    path:"view-task/:userId",
    component:ViewTasksComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"view-task",
    component:ViewTasksComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"popup",
    component: SendConfirmationComponent
  },
  {
    path:"notification",
    component: NotificationComponent,
    
  },
  {
    path:"navbar",
    component: HeaderSidenavbarComponent,
    
    
  },
  {
    path:"**" ,
    component: PageNotFoundComponent
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }