import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userName: string;

  password: string;

  msgs: any[];

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.msgs = [{severity: 'info', detail: 'UserName: admin'}, {severity: 'info', detail: 'Password: password'}];
  }

  onSubmitForm(): void {
    this.router.navigate(['main/dashboard']);
  }

}
