import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButton } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';

@Component({
  selector: 'app-deletemodal',
  imports: [CommonModule,
      MatFormFieldModule, FormsModule,MatDialogContent,],
  templateUrl: './deletemodal.component.html',
  styleUrl: './deletemodal.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DeletemodalComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data :any){
      console.log(data);
  }
}
