import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../task.service';
import {AuthService} from '../../../core/auth.service';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-task-chatgroup',
  templateUrl: './task-chatgroup.component.html',
  styleUrls: ['./task-chatgroup.component.css']
})
export class TaskChatgroupComponent implements OnInit, OnDestroy {
  message: any;
  listMessage: Array<any>;
  connection: any;
  disconnection: any;

  constructor(private taskService: TaskService, public authService: AuthService) {

    // const stompClient = this.taskService.receiveMessage();
    //     stompClient.connect({}, frame => {
    //         stompClient.subscribe<any>('/topic/notification'+this.authService.getTopicID() , res => {
    //           this.listMessage.push(JSON.parse(res.body));
    //         });
    //     });

     this.getAllMessage();
  }

  ngOnInit() {
    const stompClient = this.taskService.receiveMessage();
        stompClient.connect({}, frame => {
            this.disconnection = stompClient.subscribe<any>('/topic/notification'+this.authService.getTopicID() , res => {
              this.listMessage.push(JSON.parse(res.body));
            });
        });
  }

  ngOnDestroy() {
    this.disconnection.unsubscribe();
  }

  sendMessage(message) {
    this.taskService.sendMessage(message).subscribe(
      res => {

      }
    );
  }

  getAllMessage() {
    this.taskService.getAllMessage().subscribe(
      res => {
        this.listMessage = res;
        console.log(res);
      }
    );
  }

 isMyMessage(username){
    if(this.authService.getUsername() == username){
      return 'right';
    }
    else {
      return null;
    }
  }
}
