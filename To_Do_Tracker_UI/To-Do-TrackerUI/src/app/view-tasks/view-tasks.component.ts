import { Component, OnInit } from '@angular/core';
import { Task } from '../model/Task';
import { UserTaskService } from '../services/user-task.service';
import { ActivatedRoute, Router } from '@angular/router';
import { TaskArchiveService } from '../services/task-archive.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AddTaskComponent } from '../add-task/add-task.component';
import { UpdateTaskComponent } from '../update-task/update-task.component';
import { UpdateConfirmationComponent } from '../filtered-task/update-confirmation/update-confirmation.component';

@Component({
  selector: 'app-view-tasks',
  templateUrl: './view-tasks.component.html',
  styleUrls: ['./view-tasks.component.css']
})
export class ViewTasksComponent implements OnInit {
  notes$: any;
  searchResult: Task[] = [];
  emailId: any;
  addCount:number = 0
viewTask: any;
getPriorityClass: any;
getCardColor: any;
isHovered: boolean = false;

//  extra card ====================================================

// Declare a variable to track whether to show full card content
showFullCardContent: boolean = false;
// Declare a variable to store the full card content
fullCardContent: string = '';


selectedCardColor: string = '';

// Method to show full card content
showFullContent(note: any) {
  // Assign full card content to the variable
  this.fullCardContent = this.formatTaskContent(note.taskContent);
  // Set the background color of the clicked card
  this.selectedCardColor = 'light-' + note.taskPriorityLevel.toLowerCase();
  // Set showFullCardContent to true to display the full card
  this.showFullCardContent = true;
}

// Method to format task content (list or paragraphs)
formatTaskContent(content: string): string {
  // Check if the content contains a list
  if (content.includes('<ul>')) {
    // Render content as a list
    return content;
  } else {
    // Render content as separate paragraphs
    return content.split('\n').map(paragraph => `<p>${paragraph}</p>`).join('');
  }
}


// Method to close full card content
closeFullContent() {
  // Clear full card content
  this.fullCardContent = '';
  // Clear the background color of the clicked card
  this.selectedCardColor = '';
  // Set showFullCardContent to false to hide the full card
  this.showFullCardContent = false;
}



// ==========================================================
  buttonVisibility() {
    this.isHovered = true;
  }
  onMouseLeave() {
    this.isHovered = false;
  }

  constructor(
    private taskService: UserTaskService,
    private router: Router,
    public dialog: MatDialog,
    private actRoute: ActivatedRoute,
    private taskArc: TaskArchiveService,
    private _snackBar: MatSnackBar
  ) {}

  add() {
    const dialogRef = this.dialog.open(AddTaskComponent, {
      data: { emailId: this.emailId },
      width: '700px',
      height: '650px',
    });
  }
  
  update(taskName: any) {
    const dialogRef = this.dialog.open(UpdateTaskComponent, {
      data: { emailId: this.emailId, task: taskName },
      width: '700px',
      height: '750px',
    });
  }

  update1(taskName: any) {
    const dialogRef = this.dialog.open(UpdateConfirmationComponent, {
      data: { emailId: this.emailId, task: taskName },
      width: '300px',
      height: '200px',
    });
  }

  delete(task: any) {
    // this.addCount=this.addCount+1;
    // this.taskService.notifycount.next(this.addCount)
    if (confirm('Are you sure you want to delete this task?')) {
    this.taskService.deleteTaskByTaskId(this.emailId, task.taskName).subscribe({
      next() {
        
        alert('successfully deleted!! ');
      },
      error() {
        console.log
        ('Error from server side!! ');
      },
    });
  }
}
  getAllTask() {
    this.taskService.getAllTasksOfUser(this.emailId).subscribe((response) => {
      this.notes$ = response;
      this.searchResult = response;
    });
  }

  getTaskPrioritywise(priority:string){
    if(priority==null){
      this.taskService.getAllTasksOfUser(this.emailId).subscribe({
        next:data => {this.notes$=data },
        error() {alert ("Error occured while loading the tasks")},         
      })
    }else{
      this.taskService.getAllTasksOfUser(this.emailId).subscribe({
        next:data => { this.notes$=data.filter((task)=>
          {
            return task.taskPriorityLevel?.startsWith(priority);
          }) },
        error() {alert ("Error occured while loading the tasks")},          
      })
    }
  }


  ngOnInit(): void {
    this.emailId = this.taskService.getEmailId();
    console.log(this.emailId);
    this.getAllTask();
    this.taskService.Refresh.subscribe((response) => {
      this.getAllTask();
    });
  }

  refresh(text: any) {
    window.location.reload();
  }

  search(searchText: string) {
    if (searchText === ' ' || !searchText) this.notes$ = this.searchResult;
    else {
      console.log(this.notes$);
      console.log(this.searchResult);
      this.taskService.getAllTasksOfUser(this.emailId).subscribe({
        next: (data) => {
          this.notes$ = data.filter((task) => {
            return task.taskName
              ?.toLowerCase()
              .startsWith(searchText.toLowerCase());
          });
        },
        error() {
          alert('No result');
        },
      });
      console.log(searchText);
      console.log(this.notes$);
    }
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

getPriorityClassName(priority: string): string {
  switch (priority.toLowerCase()) {
    case 'low':
      return 'priority-low';
    case 'medium':
      return 'priority-medium';
    case 'high':
      return 'priority-high';
    case 'urgent':
      return 'priority-urgent';
    default:
      return 'priority-default'; // default class if priority is not recognized
  }
}


splitContentIntoParagraphs(content: string): string[] {
  return content.split('\n');
}


}