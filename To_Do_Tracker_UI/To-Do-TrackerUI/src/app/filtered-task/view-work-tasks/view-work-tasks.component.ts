import { Component } from '@angular/core';
import { Task } from '../../model/Task';
import { UserTaskService } from '../../services/user-task.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskArchiveService } from '../../services/task-archive.service';
import { MatDialog } from '@angular/material/dialog';
import { UpdateTaskComponent } from '../../update-task/update-task.component';

@Component({
  selector: 'app-view-work-tasks',
  templateUrl: './view-work-tasks.component.html',
  styleUrls: ['./view-work-tasks.component.css']
})
export class ViewWorkTasksComponent {
  emailId:any;
  tasks:Task[] = [];
  constructor(private taskService: UserTaskService, private router: Router,
    public dialog: MatDialog, private actRoute: ActivatedRoute, private taskArc: TaskArchiveService) { }

  getWorkTask(){                                                             //to view personal tasks
    this.taskService.getAllTasksOfUser(this.emailId).subscribe({
      next:data => { this.tasks=data.filter((task)=>
        {
          return task.taskCategory?.startsWith("Work");
        }) },
      error() {alert ("Error occured while loading the work tasks")},          
    })
  }

  ngOnInit(): void {
    this.emailId = this.taskService.getEmailId()
    console.log(this.emailId);
    this.getWorkTask();
    this.taskService.Refresh.subscribe(res=>{
      this.getWorkTask()
    })
   }

   
    delete(note: any) {
      this.taskService.deleteTaskByTaskId(this.emailId, note.taskName).subscribe()
      window.location.reload();
    }
  
  
    update(taskname: any) {
      const dialogRef = this.dialog.open(UpdateTaskComponent, {
        data: { emailId: this.emailId, task: taskname },
        width: "700px",
        height: "900px"
      })
    }
}

