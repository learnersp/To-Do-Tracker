// import { Component, OnInit } from '@angular/core';
// import { Task } from '../../model/Task';
// import { UserTaskService } from '../../services/user-task.service';
// import { ActivatedRoute, Router } from '@angular/router';
// import { MatDialog } from '@angular/material/dialog';
// import { TaskArchiveService } from '../../services/task-archive.service';
// import { AddTaskComponent } from '../../add-task/add-task.component';
// import { UpdateTaskComponent } from '../../update-task/update-task.component';
// import { SendConfirmationComponent } from '../send-confirmation/send-confirmation.component';

// @Component({
//   selector: 'app-view-completed-task',
//   templateUrl: './view-completed-task.component.html',
//   styleUrls: ['./view-completed-task.component.css']
// })
// export class ViewCompletedTaskComponent implements OnInit{
// move(_t10: Task) {
// throw new Error('Method not implemented.');
// }
// update(arg0: string|undefined) {
// throw new Error('Method not implemented.');
// }

//   notes: Task[] = [];
//   emailId: any;



//   constructor(private taskService: UserTaskService, private router: Router,
//     public dialog: MatDialog, private actRoute: ActivatedRoute, private taskArc: TaskArchiveService) { }

//   ngOnInit(): void {
//     this.emailId = this.taskService.getEmailId()
//       console.log(this.emailId);
//       this.getCompletedTask()
//       this.taskService.Refresh.subscribe(res=>{
//         this.getCompletedTask()
//       })
     
  
//     }

//     getCompletedTask(){
//       this.taskService.getCompletedTask(this.emailId).subscribe(response => {
//         this.notes = response
//         console.log(this.emailId);})
//     }
//   }

import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { Task } from '../../model/Task';
import { TaskArchiveService } from '../../services/task-archive.service';
import { UserTaskService } from '../../services/user-task.service';
import { AddTaskComponent } from '../../add-task/add-task.component';
import { UpdateTaskComponent } from '../../update-task/update-task.component';
import { SendConfirmationComponent } from '../send-confirmation/send-confirmation.component';

@Component({
  selector: 'app-view-completed-task',
  templateUrl: './view-completed-task.component.html',
  styleUrls: ['./view-completed-task.component.css']
})
export class ViewCompletedTaskComponent implements OnInit {


  notes: Task[] = [];
  emailId: any;



  constructor(private taskService: UserTaskService, private router: Router,
    public dialog: MatDialog, private actRoute: ActivatedRoute, private taskArc: TaskArchiveService) { }


    ngOnInit(): void {
      this.emailId = this.taskService.getEmailId()
      console.log(this.emailId);
      this.getCompletedTask()
      this.taskService.Refresh.subscribe(res=>{
        this.getCompletedTask()
      })
     
  
    }

    getCompletedTask(){
      this.taskService.getCompletedTask(this.emailId).subscribe(response => {
        this.notes = response
        console.log(this.emailId);})
    }

    move(note: any) {
      const dialogRef = this.dialog.open(SendConfirmationComponent, {
        data: { emailId: this.emailId, task: note },
        width: "400px",
        height: "247px" })
    }


    update(taskname: any) {
      const dialogRef = this.dialog.open(UpdateTaskComponent, {
        data: { emailId: this.emailId, task: taskname },
        width: "700px",
        height: "900px"
      })
    }

    getPriorityColor(priorityLevel: string): string {
      switch (priorityLevel) {
        case 'Critical':
        return 'hsl(0, 100%, 90%)'; // Light red for critical priority
      case 'High':
        return 'hsl(30, 100%, 90%)'; // Light orange for high priority
      case 'Medium':
        return 'hsl(60, 100%, 90%)'; // Light yellow for medium priority
      case 'Low':
        return 'hsl(120, 100%, 90%)'; // Light green for low priority
      default:
        return '#f0f0f0'; 
    }
    
  }

  }