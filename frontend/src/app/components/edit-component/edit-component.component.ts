import { CommonModule } from '@angular/common';
import { Component, Inject, inject, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogContent, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';

@Component({
  selector: 'app-edit-component',
  imports: [CommonModule,
    MatFormFieldModule, FormsModule,MatDialogContent,MatInput,MatButtonModule],
  templateUrl: './edit-component.component.html',
  styleUrl: './edit-component.component.css'
})
export class EditComponentComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data :any){
    console.log(data);
    
  }
}
