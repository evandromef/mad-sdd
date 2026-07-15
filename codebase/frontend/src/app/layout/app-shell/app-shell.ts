import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-shell',
  imports: [ButtonModule, RouterOutlet],
  templateUrl: './app-shell.html',
  styleUrl: './app-shell.css'
})
export class AppShell {
  protected readonly navigationItems = ['Dashboard', 'Carteiras', 'Ativos', 'Lançamentos'];
}
