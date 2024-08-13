import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchControl = new FormControl();
  searchText: string = '';

  @Output() searchs: EventEmitter<string> = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
    // Subscribe to changes in the search input field
    this.searchControl.valueChanges.pipe(
      debounceTime(300), // wait for 300ms after the last input event
      distinctUntilChanged() // emit only if the value has changed
    ).subscribe((value) => {
      this.searchText = value;
      this.search();
    });
  }

  search() {
    if (!this.searchText.trim()) {
      this.searchs.emit(''); // Emit empty string if search text is empty
    } else {
      this.searchs.emit(this.searchText);
    }
  }
}