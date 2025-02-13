import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./components/header/header.component";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent,FormsModule],
  template: `
    <app-header></app-header>
    <main>
      <router-outlet/>
    </main>
  `,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
