import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AddTaskComponent } from './add-task/add-task.component';
import { ArchiveTaskCardComponent } from './archive-task-card/archive-task-card.component';
import { HeaderSidenavbarComponent } from './header-sidenavbar/header-sidenavbar.component';
import { HelpcenterComponent } from './helpcenter/helpcenter.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NotificationComponent } from './notification/notification.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegisterComponent } from './register/register.component';
import { SearchComponent } from './search/search.component';
import { SettingsComponent } from './settings/settings.component';
import { UpdateTaskComponent } from './update-task/update-task.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ViewArchiveTaskComponent } from './view-archive-task/view-archive-task.component';
import { ViewFilteredTaskComponent } from './view-filtered-task/view-filtered-task.component';
import { ViewTasksComponent } from './view-tasks/view-tasks.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import {MatChipsModule} from '@angular/material/chips';
import {MatBadgeModule} from '@angular/material/badge';
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { MatListModule } from '@angular/material/list';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select'; 
import { UserTaskService } from './services/user-task.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { UserAuthenticationService } from './services/user-authentication.service';
import { TaskArchiveService } from './services/task-archive.service';
import { NotificationService } from './services/notification.service';
import { FormsModule } from '@angular/forms';
import { MatGridListModule } from '@angular/material/grid-list';
import { SendConfirmationComponent } from './filtered-task/send-confirmation/send-confirmation.component';
import { UpdateConfirmationComponent } from './filtered-task/update-confirmation/update-confirmation.component';
import { ViewCompletedTaskComponent } from './filtered-task/view-completed-task/view-completed-task.component';
import { ViewPersonalTasksComponent } from './filtered-task/view-personal-tasks/view-personal-tasks.component';
import { ViewTodayTasksComponent } from './filtered-task/view-today-tasks/view-today-tasks.component';
import { ViewUpcomingTasksComponent } from './filtered-task/view-upcoming-tasks/view-upcoming-tasks.component';
import { ViewWorkTasksComponent } from './filtered-task/view-work-tasks/view-work-tasks.component';
import { AuthInterceptor } from './services/authconfig.interceptor';
import { MatInputModule} from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatRippleModule } from '@angular/material/core';
import {MatDialogModule} from '@angular/material/dialog';
import { MatNativeDateModule } from '@angular/material/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatMenuModule } from '@angular/material/menu';


@NgModule({
  declarations: [
    AppComponent,
    AddTaskComponent,
    ArchiveTaskCardComponent,
    HeaderSidenavbarComponent,
    HelpcenterComponent,
    HomeComponent,
    LoginComponent,
    NotificationComponent,
    PageNotFoundComponent,
    RegisterComponent,
    SearchComponent,
    SettingsComponent,
    UpdateTaskComponent,
    UserProfileComponent,
    ViewArchiveTaskComponent,
    ViewFilteredTaskComponent,
    ViewTasksComponent,
    SendConfirmationComponent,
    UpdateConfirmationComponent,
    ViewCompletedTaskComponent,
    ViewPersonalTasksComponent,
    ViewTodayTasksComponent,
    ViewUpcomingTasksComponent,
    ViewWorkTasksComponent,
    
     
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule, 
    MatIconModule,
    MatButtonModule,
    MatRadioModule,
    MatChipsModule,
    MatBadgeModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatListModule,
    MatDatepickerModule,
    MatSelectModule,
    HttpClientModule,
    FormsModule,
    MatGridListModule,
    MatInputModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatRippleModule,
    MatDialogModule,
    MatNativeDateModule,
    MatProgressBarModule,
    MatExpansionModule,
    MatMenuModule
    
  ],
  providers: [
    provideAnimationsAsync(),
    UserTaskService,
    TaskArchiveService,
    NotificationService,
    UserAuthenticationService,
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
    }
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
