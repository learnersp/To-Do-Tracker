import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserTaskService } from '../services/user-task.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ViewTasksComponent } from '../view-tasks/view-tasks.component';
import { Task } from '../model/Task';
import moment from 'moment';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css']
})
export class UpdateTaskComponent implements OnInit {
  task: Task = {
    taskDeadline: undefined
  };
  todayDate = new Date();

  emailId: any = this.data.emailId;
  task1: any = this.data.task;

  constructor(
    private taskService: UserTaskService,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: { emailId: any; task: any },
    public dialogRef: MatDialogRef<ViewTasksComponent>
  ) { }

  ngOnInit(): void {
    this.taskService.getTaskByTaskId(this.emailId, this.task1)
      .subscribe((res) => {
        this.task = res;
        console.log('Task:', this.task);

        console.log(this.emailId);

        // Get today's date in YYYY-MM-DD format
        let today = moment().format('YYYY-MM-DD');
        console.log(today);

        // Apply property to parse taskDeadline if it's a Date
        if (this.task.taskDeadline instanceof Date) {
          const selectedDate = this.task.taskDeadline;

          // Use moment.utc to interpret the date in UTC time
          const parsedMoment = moment.utc(selectedDate, 'YYYY-MM-DD'); // Use correct format if needed
          const parsedDate = parsedMoment.toDate();

          // Assign parsedDate to taskDeadline
          this.task.taskDeadline = parsedDate;
        }
      });
  }

  updateTaskDeadline(event: any) {
    if (this.task.taskDeadline instanceof Date) {
      const selectedDate = this.task.taskDeadline;
      // Create a Date object from the formatted string
      const parsedDate = new Date(moment(selectedDate).format('YYYY-MM-DD'));
      this.task.taskDeadline = parsedDate;
    }
  }

  function1() {
    this.updateTaskDeadline(this.task.taskDeadline); // Call the update function before updateTask
    this.taskService.updateTask(this.emailId, this.task).subscribe();
    console.log(this.task);
    this.dialogRef.close();
  }
}