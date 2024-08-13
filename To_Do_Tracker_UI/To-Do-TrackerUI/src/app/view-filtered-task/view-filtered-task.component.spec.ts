import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFilteredTaskComponent } from './view-filtered-task.component';

describe('ViewFilteredTaskComponent', () => {
  let component: ViewFilteredTaskComponent;
  let fixture: ComponentFixture<ViewFilteredTaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewFilteredTaskComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewFilteredTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
