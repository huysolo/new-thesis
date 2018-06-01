import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-review-topic-page',
  templateUrl: './review-topic-page.component.html',
  styleUrls: ['./review-topic-page.component.css']
})
export class ReviewTopicPageComponent implements OnInit {

  navLinks = [{
    param: 'guide',
    content: 'Supervise'
  }, {
    param: 'review',
    content: 'Debate'
  }]
  
  constructor() { }
  ngOnInit() {
  }

}
