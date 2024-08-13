import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserTaskService } from '../services/user-task.service';

interface RegistrationResponse {
  firstName: string;
  // Add other properties based on the actual response structure
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registerForm = this.fb.group({
    firstName: ['', [
      Validators.required,
      Validators.minLength(2),
      // this.noSpaces, // Use the custom validator for no spaces
      // this.noNumbers, // Use the custom validator for no numbers
      // this.noSpecialChars // Use the custom validator for no special characters
      this.noSpacesOrNumbersOrSpecialChars
    ]],
    lastName: ['', [
      Validators.required,
      Validators.minLength(2),
      // this.noSpaces,
      // this.noNumbers,
      // this.noSpecialChars
      this.noSpacesOrNumbersOrSpecialChars
    ]],
    emailId: ['', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
    password: ['', [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
  });
  formData = new FormData();
  file: any;
  student: any;
  
  fileUrl: any; // Add this property
  uploading: boolean = false;

  // Employee: any;

  constructor(private fb: FormBuilder, private _snackBar: MatSnackBar, private router: Router, private userTaskSer: UserTaskService) { }

  // onFileSelect(event: any): void {
  //   this.file = event.target.files[0];
  //   console.log(this.file);
  //   this.formData.append("file", this.file);
  // }
  onFileSelect(event: any): void {
    this.file = event.target.files[0];
    this.uploading = true; // Set uploading flag to true when file upload starts
    
    this.fileUrl = URL.createObjectURL(this.file); // Set the file directly as the URL
    
    this.uploading = false; // Set uploading flag to false after setting the URL
    this.formData.append("file", this.file);
  }

   //for preventing the other mat form field red validation errors due to browser focus on this file input
   triggerFileInput(event: any): void {
    event.preventDefault(); // Prevent the default action
    const fileInput = document.getElementById('upload');
    fileInput?.click(); // Trigger click event on file input
  }

  onSubmit(): void {
    if (this.file == null) {
      console.log("user");
      console.log(this.registerForm.value);
      this.userTaskSer.registerUser(this.registerForm.value).subscribe(
        response => {

          this._snackBar.open('Congrats!!You have submitted the form!! Hello  {' + response.firstName + '}', 'success', {
            duration: 5000,
            panelClass: ['mat-toolbar', 'mat-primary']
          });
          this.registerForm.reset();
          this.router.navigate(['login'])
        }
      ), (err: HttpErrorResponse) => {
        if (err.status == 409) {
          alert("Registration failed! User Already Exists");
          console.log(err.message);
        } else {
          alert("Server Site Problem");
          console.log(err.message);
        }
      }
    } else {

      this.formData.append("user", JSON.stringify(this.registerForm.value))
      this.userTaskSer.registerUser(this.formData).subscribe(response => {
        this._snackBar.open('Congrats!!You have submitted the form!! Hello  {' + response.firstName + '}', 'success', {
          duration: 5000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
        this.registerForm.reset();
        this.router.navigate(['login'])
      },
        (error: HttpErrorResponse) => {

          if (error.status == 409) {
            alert("Registration failed! User Already Exists");
            console.log(error.message);
          } else {
            alert("Server Site Problem");
            console.log(error.message);
          }


        }
      )
    }
  }

  registerRoutefunc(): void {
    this.router.navigateByUrl("login");
  }

  get firstName(): AbstractControl | null { return this.registerForm.get("firstName"); }
  get lastName(): AbstractControl | null { return this.registerForm.get("lastName"); }
  get emailId(): AbstractControl | null { return this.registerForm.get("emailId"); }
  get password(): AbstractControl | null { return this.registerForm.get("password"); }

  show: boolean = false;

  passwordFunc(): void {
    this.show = !this.show;
  }

  private handleSuccessfulRegistration(response: RegistrationResponse): void {
    this._snackBar.open(`Congrats!! You have submitted the form!! Hello ${response.firstName}`, 'success', {
      duration: 5000,
      panelClass: ['mat-toolbar', 'mat-primary'],
    });
    this.registerForm.reset();
    this.router.navigate(['login']);
  }

  private handleRegistrationError(error: HttpErrorResponse): void {
    if (error.status === 409) {
      alert("Registration failed! User Already Exists");
      console.log(error.message);
    } else {
      alert("Server Site Problem");
      console.log(error.message);
    }
  }

  // Custom Validator for no spaces
  // noSpaces(control: AbstractControl) {
  //   if (control.value && control.value.trim().length === 0) {
  //     return { noSpaces: true };
  //   }
  //   return null;
  // }

  // Custom Validator for no numbers
  // noNumbers(control: AbstractControl) {
  //   if (control.value && /\d/.test(control.value)) {
  //     return { noNumbers: true };
  //   }
  //   return null;
  // }

  // Custom Validator for no special characters
  // noSpecialChars(control: AbstractControl) {
  //   if (control.value && !/^[a-zA-Z0-9]*$/.test(control.value)) {
  //     return { noSpecialChars: true };
  //   }
  //   return null;
  // }

  noSpacesOrNumbersOrSpecialChars(control: AbstractControl) {
    if (control.value && control.value.trim().length !== 0) { // Check for empty value first
      if (/\d/.test(control.value)) {
        return { noNumbers: true };
      } else if (!/^[a-zA-Z0-9]*$/.test(control.value)) {
        return { noSpecialChars: true };
      } else if (control.value !== control.value.trim()) {
        return { noSpaces: true };
      }
    }
    return null;
  }
  

  togglePasswordVisibility(): void {
    this.show = !this.show;
  }

  getErrorMessage(): string {
    return 'Invalid email format (e.g. - ex@gmail.com)';
  }
}