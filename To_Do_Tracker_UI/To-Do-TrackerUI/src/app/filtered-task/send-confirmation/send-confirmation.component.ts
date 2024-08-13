import { Component, Inject } from '@angular/core';
import { Task } from '../../model/Task';
import { UserTaskService } from '../../services/user-task.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TaskArchiveService } from '../../services/task-archive.service';
import { ViewCompletedTaskComponent } from '../view-completed-task/view-completed-task.component';

@Component({
  selector: 'app-send-confirmation',
  templateUrl: './send-confirmation.component.html',
  styleUrls: ['./send-confirmation.component.css']
})
export class SendConfirmationComponent {
  task = new Task()
 
  emailId: any = this.data.emailId;
  task1 :any =this.data.task;

  constructor(
    private taskService: UserTaskService,
    private router: Router,@Inject(MAT_DIALOG_DATA) public data :{ emailId: any ,task:any},
    private taskarc: TaskArchiveService,public dialogRef: MatDialogRef<ViewCompletedTaskComponent>) { }




 ngOnInit(): void {

   this.taskService.getTaskByTaskId(this.emailId,this.task1.taskName).subscribe(res => this.task = res)
   console.log(this.task1);
   console.log("user details"+this.emailId);

 }

move(task: any) {
   this.taskarc.addTaskInArchive(task, this.emailId).subscribe(data => { console.log(task) });
   this.taskService.deleteTaskByTaskId(this.emailId, task.taskName).subscribe(() => alert("Successfully move to archive!!"))
   
   this.dialogRef.close();
 }
 
 cancel(){
   this.dialogRef.close()
 }
}

