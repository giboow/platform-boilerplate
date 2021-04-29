import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userName: string;

  password: string;

  msgs: any[];

  constructor() { }

  ngOnInit(): void {
    this.msgs = [{ severity: 'info', detail: 'UserName: admin' }, { severity: 'info', detail: 'Password: password' }];
  }

  onClickLogin(): void{
    console.log(1);
  }

}
