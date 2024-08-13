import { Component, OnInit } from '@angular/core';
import { UserTaskService } from '../services/user-task.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { TaskArchiveService } from '../services/task-archive.service';
import { UserAuthenticationService } from '../services/user-authentication.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ViewCompletedTaskComponent } from '../filtered-task/view-completed-task/view-completed-task.component';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  userId: any;
  userDetail: any = {};
  
  
  constructor(
    private _snackBar: MatSnackBar,
    private taskService: UserTaskService,
     private router: Router,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<LoginComponent>,
     private authService :UserAuthenticationService,) { }
  
ngOnInit(): void {
  this.userId = this.taskService.getEmailId()
  this.taskService.getUserById(this.userId).subscribe(data=>{
  this.userDetail=data 
 
})}

  logOutFunc() {
    this.userDetail = null; // Clear userDetail upon logout
    this.authService.logout();
    this.taskService.removeEmail();
    window.location.href = '/home'
    // this.router.navigateByUrl('home');
    this._snackBar.open('You have logged out', 'success', {
      duration: 5000,
      panelClass: ['mat-toolbar', 'mat-primary']
    });
    if (this.dialogRef) {
      this.dialogRef.close();
    }
}

}