import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DisapproveDirective } from '../disapprove.directive';

@Component({
  selector: 'app-disapprove',
  templateUrl: './disapprove.component.html',
  styleUrls: ['./disapprove.component.css']
})
export class DisapproveComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<DisapproveDirective>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
