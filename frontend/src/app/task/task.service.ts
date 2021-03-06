import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { TaskInfo } from './components/task-info';

import * as SockJs from 'sockjs-client';
import * as Stomp from 'stompjs';
import { UserUpload } from '../models/UserUpload';
import { AuthService } from '../core/auth.service';
import { Task } from '../models/Task';

@Injectable()
export class TaskService {
  taskStdList: Array<any>;
  private socket;

  constructor(private httpClient: HttpClient, private authSv: AuthService) { }



  createtask(task: TaskInfo) {
    const loginUrl = `http://localhost:8080/crttask`;
    return this.httpClient.post<TaskInfo>(loginUrl, task);
  }

  editTask(task: TaskInfo) {
    const loginUrl = `http://localhost:8080/edittask`;
    return this.httpClient.post<TaskInfo>(loginUrl, task);
  }

  getlistTask(topicID: number) {
    const loginUrl = `http://localhost:8080/getlisttask`;
    return this.httpClient.get<any>(loginUrl + '?topicID=' + topicID);
  }

  getPage(topicID: number, pageNumber: Number, title) {
    const loginUrl = `http://localhost:8080/getlisttask`;
    return this.httpClient.get<any>(loginUrl + '?topicID=' + topicID + '&page=' + pageNumber + '&title=' + title);
  }

  getAllStudentDoTopic() {
    const loginUrl = `http://localhost:8080/getallstd`;
    return this.httpClient.get<any>(loginUrl);
  }

  submitTask(taskID: number, submit: number) {
    const loginUrl = `http://localhost:8080/submittask`;
    return this.httpClient.get<any>(loginUrl + '?taskID=' + taskID + '&submit=' + submit + '');
  }

  reviewTask(taskID: number, pass: number) {
    const loginUrl = `http://localhost:8080/reviewtask`;
    return this.httpClient.get<any>(loginUrl + '?taskID=' + taskID + '&pass=' + pass + '');
  }

  getTopicCount() {
    const loginUrl = `http://localhost:8080/topiccount`;
    return this.httpClient.get<any>(loginUrl);
  }

  getSemCount() {
    const Url = `http://localhost:8080/semcount`;
    return this.httpClient.get<any>(Url);
  }

  getTopicFromSemID(semid) {
    const loginUrl = `http://localhost:8080/getlisttopic`;
    return this.httpClient.get<any>(loginUrl + '?semid=' + semid);
  }

  getTopicFromStd() {
    const loginUrl = `http://localhost:8080/stdgetlisttopic`;
    return this.httpClient.get<any>(loginUrl);
  }

  receiveMessage() {
    const socket = new SockJs(`http://localhost:8080/socket`);
    const stompClient = Stomp.over(socket);
    return stompClient;
  }

  sendMessage(message) {
    const loginUrl = `http://localhost:8080/notify`;
    return this.httpClient.get<any>(loginUrl + '?message=' + message);
  }

  getAllMessage() {
    const loginUrl = `http://localhost:8080/getallmessage`;
    return this.httpClient.get<any>(loginUrl);
  }

  getTaskComment(taskID: number) {
    const loginUrl = `http://localhost:8080/gettaskcomment`;
    return this.httpClient.get<any>(loginUrl  + '?taskid=' + taskID);
  }

  sendComment(comment: String, taskid: number) {
    const Url = `http://localhost:8080/taskcomment`;
    return this.httpClient.get<any>(Url + '?comment=' + comment + '&taskid=' + taskid + '');
  }

  getListUserUpload(taskId) {
    const param = new HttpParams().append('id', taskId.toString());
    return this.httpClient.get<UserUpload[]>('http://localhost:8080/getStudents', {params: param});
  }

  isBelongToTask(listUserTask: any[]) {
    return listUserTask.filter(task => task.userId == this.authSv.getUserId()).length === 1;
  }

  getListTaskByApprove(approve) {
    const param = new HttpParams().append('approve', approve);
    return this.httpClient.get<Task[]>('http://localhost:8080/tasksapprove', {params: param});
  }

  countTask() {
    return this.httpClient.get<number>('http://localhost:8080/taskcount');
  }

  countTaskByStd() {
    return this.httpClient.get<number>('http://localhost:8080/counttaskbystd');
  }

  countMessgeByStd() {
    return this.httpClient.get<number>('http://localhost:8080/countmessagebystd');
  }

  stdGetTopicID() {
    return this.httpClient.get<number>('http://localhost:8080/stdgettopicid');
  }

  getTaskById(id) {
    return this.httpClient.get<Task>('http://localhost:8080/task?id=' + id);
  }

  getMyTasks(){
    return this.httpClient.get<Task[]>('http://localhost:8080/getmytasks');
  }
}
