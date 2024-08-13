import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '../services/notification.service';
import { TaskArchiveService } from '../services/task-archive.service';
import { UserTaskService } from '../services/user-task.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {
  constructor(
    private taskService: UserTaskService,
    private notificationService: NotificationService,
    private router: Router,
    private actRoute: ActivatedRoute,
    private taskArc: TaskArchiveService
  ) {}

  userId: any;
  notification$: any;
  // addCount: number = 1;

  ngOnInit(): void {
    this.userId = this.taskService.getEmailId();
    this.getAllNotification();
    console.log(this.notification$);
  }

  getAllNotification() {
    this.notificationService.getAllTasksFromNotification(this.userId).subscribe((response) => {
      console.log(response);
      this.notification$ = response;
      console.log(this.notification$);
    });
  }

  // delete(taskname: any) {
  //   this.addCount = this.addCount - 1;
  //   this.taskService.notifycount.next(this.addCount);
  //   this.notificationService.deleteTaskFromNotification(this.userId, taskname).subscribe((data) => {
  //     this.ngOnInit();
  //   });
  // }
  delete(taskname: any) {
    if (confirm('Are you sure you want to delete this notification?')) {
      // this.addCount = 0;
      // this.taskService.notifycount.next(this.addCount);
      this.notificationService.deleteTaskFromNotification(this.userId, taskname).subscribe(
        () => {
          // Remove the deleted notification from the array
          this.notification$ = this.notification$.filter((notification: any) => notification.taskName !== taskname);
        },
        (error) => {
          console.error('Error deleting notification:', error);
        }
      );
    }
  }
}