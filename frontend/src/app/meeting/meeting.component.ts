import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-meeting',
  templateUrl: './meeting.component.html',
  styleUrls: ['./meeting.component.css']
})
export class MeetingComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  Time = [{ name: "box" }, { name: "box" }, { name: "box" }, { name: "box" }, { name: "box" }, { name: "box" }];
  Day = [{ name: "box" }, { name: "box" }, { name: "box" }, { name: "box" }, { name: "box" }, { name: "box" }, { name: "box" }];

  style1 = {
    'background-color' : 'red'
  }
  style2 = {
    color: 'blue'
  }
  render = [{
    x:0,
    y:1
  }, {
    x:0,
    y:2
  }, {
    x:0,
    y:3
  }];
  isBook(x:number, y: number): Boolean{
    for(let i = 0, len = this.render.length; i < len ; i++){
      if(this.render[i].x == x && this.render[i].y == y){
        return true
      }
    }
    return false;
  }
}
