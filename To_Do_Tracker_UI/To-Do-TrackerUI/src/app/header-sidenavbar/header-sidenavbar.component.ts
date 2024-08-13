import { MediaMatcher } from '@angular/cdk/layout';
import { ChangeDetectorRef, Component, HostBinding, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserTaskService } from '../services/user-task.service';
import { UserAuthenticationService } from '../services/user-authentication.service';
import { OverlayContainer } from '@angular/cdk/overlay';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { UserProfileComponent } from '../user-profile/user-profile.component';
import { NotificationComponent } from '../notification/notification.component';

@Component({
  selector: 'app-header-sidenavbar',
  templateUrl: './header-sidenavbar.component.html',
  styleUrls: ['./header-sidenavbar.component.css']
})
export class HeaderSidenavbarComponent implements OnInit, OnDestroy {
  userId: any;
  userDetail: any = {};
  notifycount?:number;



  mobileQuery: MediaQueryList;
  private _mobileQueryListener: () => void;

  toggleControl = new FormControl(false);
  @HostBinding('class') className = '';
  darkClassName = 'theme-dark';
  lightClassName = 'theme-light';

  constructor(
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    private router: Router,
    private service: UserTaskService,
    private authService: UserAuthenticationService,
    public dialog: MatDialog,
    private overlay: OverlayContainer
  ) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
    this.service.notifycount.subscribe((res: number | undefined) => {
      this.notifycount = res;
    });
  }

  ngOnInit(): void {
    this.toggleControl.valueChanges.subscribe((darkMode) => {
      this.className = darkMode ? this.darkClassName : this.lightClassName;
      if (darkMode) {
        this.overlay.getContainerElement().classList.add(this.darkClassName);
      } else {
        this.overlay.getContainerElement().classList.remove(this.darkClassName);
      }
    });
    this.userId = this.service.getEmailId();
    this.getUserData();
  }
  

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  getUserData() {
    this.service.getUserById(this.userId).subscribe(data => {
      this.userDetail = data;
    });
  }

  methodToGet() {
    this.router.navigate(['view-task']);
  }

  methodToGetId() {
    this.router.navigate(['view-archive-task']);
  }

  userProfileRouteFunc() {
    const dialogRef = this.dialog.open(UserProfileComponent, {
      width: '500px',
      height: '510px'
    });
  }

  notification() {
    const dialogRef = this.dialog.open(NotificationComponent, {
      width: '400px',
      height: '410px',
      position: { top: '0px', right: '0px' }
    });
  }

  logOutFunc() {
    this.authService.logout();
    this.service.removeEmail();
    window.location.reload();
    this.router.navigateByUrl('home');
  }
}
