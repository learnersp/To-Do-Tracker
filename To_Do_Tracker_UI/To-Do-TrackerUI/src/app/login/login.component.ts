import { Component, Injectable, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserAuthenticationService } from '../services/user-authentication.service';
import { Router } from '@angular/router';
import { UserTaskService } from '../services/user-task.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls:[  './login.component.css'],
})

export class LoginComponent  {
  loginForm:FormGroup;
  authService: any;

  constructor(private _snackBar: MatSnackBar, private taskService: UserAuthenticationService, private router: Router, private userTaskSer: UserTaskService,private fb:FormBuilder) { 
  this.loginForm = this.fb.group({
    // emailId: new FormControl('', Validators.required),
    emailId:['', Validators.required],
   // password: new FormControl('', Validators.required)
    password: ['', [Validators.required]],

   

  })
}
  email: string = "";

  get emailId() {
    return this.loginForm.get('emailId');
  }
  get password() {
    return this.loginForm.get('password');
  }
  


  // ngOnInit(): void {
  //   throw new Error('Method not implemented.');
  // }
  togglePasswordVisibility():void{
    this.show = !this.show;
  }
  // for displaying email error message
  getErrorMessage(): string {
    return 'Invalid email format (e.g - ex@gmail.com)';
  }




  onLogin() {
    this.taskService.generateToken(this.loginForm.value).subscribe(
      (response: any) => {
        this.taskService.loginUser(response.secretKeyToken.token);
        this.taskService.hideIcon = true
        this.userTaskSer.captureEmail(response.user.emailId)
        this.router.navigateByUrl('view-task')
        this._snackBar.open('Congrats!!You have logged In!!', 'success', {
          duration: 5000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });

      },
      (err) => {
        alert("invalid credentials")
        console.log(err.message);
      })
    // window.location.reload()
  }

  registerRoutefunc() {
    this.router.navigateByUrl("register")
  }

  show: boolean = false;
  passwordFunc() {
    this.show = !this.show;
  }

}
