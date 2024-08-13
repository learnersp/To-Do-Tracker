import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserTaskService } from '../services/user-task.service';
import { ViewTasksComponent } from '../view-tasks/view-tasks.component';
import { HttpErrorResponse } from '@angular/common/http';
import { Task } from '../model/Task';
import { Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import moment from 'moment';


@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit{

  emailId: any = this.data.emailId; //emailId
  task: Task = {
    taskDeadline: undefined
  };
  todayDate = new Date();
  // addCount: number = 0;

  addTaskForm = this.formBuilder.group({
    taskName: ['', [Validators.required, Validators.maxLength(30)]],
    taskContent: ['', [Validators.required, Validators.maxLength(503)]],
    taskDeadline: new FormControl(null, [Validators.required]),
    taskCategory: ['', [Validators.required]],
    taskPriorityLevel: ['', [Validators.required]],
    taskCompleted: [false, [Validators.required]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private taskService: UserTaskService,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: { emailId: number },
    public dialogRef: MatDialogRef<ViewTasksComponent>
  ) {}

  ngOnInit(): void {
    console.log(this.emailId);
    let today = moment().format('YYYY-MM-DD');

    console.log(today);
  }

  addSubmit() {
      
    if (this.task.taskDeadline instanceof Date) {
      const selectedDate = this.task.taskDeadline;
      // Create a Date object from the formatted string
      const parsedDate = new Date(moment(selectedDate).format('YYYY-MM-DD'));
      this.task.taskDeadline = parsedDate;
    }
      
        // this.addCount=this.addCount+1;
        // this.taskService.notifycount.next(this.addCount)
        this.taskService.addTask(this.emailId, this.task).subscribe((response: Task) => {

          this.task.taskName = response.taskName;
          this.task.taskContent = response.taskContent;
          this.task.taskDeadline = response.taskDeadline;
          this.task.taskCategory = response.taskCategory;
          this.task.taskPriorityLevel = response.taskPriorityLevel;
          this.task.taskCompleted = response.taskCompleted;
          console.log(this.emailId);
          console.log(this.addTaskForm.value);
          this.dialogRef.close()

        },(error:HttpErrorResponse) => {
          if(error.status==409){
          console.log(error.message);
          alert("Task with specified detail already exists.")   }
          else{
            alert("server site problem")
          }
        })
      
    
    console.log(this.task);
  }

  registerRoutefunc() {
    this.router.navigateByUrl("navbar")
  }
}
