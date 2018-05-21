import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../core/auth.service';
import { CurrUserInfo } from '../login/curr-user-info';
import { ReactiveFormsModule } from '@angular/forms';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { LoginService } from '../login/login.service';
import { ManageUser } from '../../../models/ManageUser';
import { MatSnackBar } from '@angular/material';
import { LayoutService } from '../../../layout/layout.service';


@Component({
  selector: 'app-manage-account',
  templateUrl: './manage-account.component.html',
  styleUrls: ['./manage-account.component.css']
})
export class ManageAccountComponent implements OnInit {
  currUserInfo: CurrUserInfo;
  profileForm: FormGroup;
  isEditUsername: Boolean;
  isEditEmail: Boolean;
  isLoading: Boolean;
  isSuccess: Boolean;
  isFail: Boolean;
  manageUser: ManageUser;
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);
  constructor(public authService: AuthService, private fb: FormBuilder, public loginService: LoginService,
    public snackBar: MatSnackBar, public layouSv: LayoutService) {
      layouSv.labelName = 'Manage Account';


  }

  ngOnInit() {
    this.loginService.loadProfile().subscribe(data => {
      this.manageUser = data;
    });
  }

  edit(form) {
    this.isFail = false;
    this.isLoading = true;
    this.loginService.prifile(form).subscribe(
      res => {
        this.isLoading = false;
        this.isEditUsername = res.editUsername;
        this.isEditEmail = res.editEmail;
        if (this.isEditUsername === true && this.isEditEmail === true) {
          this.isSuccess = true;
          localStorage.setItem('username', form.username);
          localStorage.setItem('password', form.password);
          localStorage.setItem('firstname', form.fistname);
          localStorage.setItem('lastname', form.lastname);
          localStorage.setItem('email', form.email);
          localStorage.setItem('gender', form.gender);
          localStorage.setItem('degree', form.degree);
          localStorage.setItem('skills', form.skills);
        } else {
          this.isFail = true;
        }
      },
      err => {
        console.log(err);
      }
    );
  }
  postProfile() {
    this.loginService.postProfile(this.manageUser).subscribe(data => {
      this.snackBar.open('Update Profile Success', 'Close', {
        duration: 2000,
      });
    });
  }

}
