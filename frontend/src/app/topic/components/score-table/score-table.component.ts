import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TopicSemStandard } from '../../../models/TopicSemStandard';

@Component({
  selector: 'app-score-table',
  templateUrl: './score-table.component.html',
  styleUrls: ['./score-table.component.css']
})
export class ScoreTableComponent implements OnInit {

  @Input() topicRvStandardDetail: TopicSemStandard[];
  @Output() close = new EventEmitter<boolean>();
  constructor() { }

  ngOnInit() {
  }



  finalScored() {
    let rs = 0;
    let co = 0;
    if (this.topicRvStandardDetail != null) {
        this.topicRvStandardDetail.forEach((dt, i) => {
            co = co + dt.coefficient ;
            rs = rs + dt.score * dt.coefficient ;
        });

    }
    return (rs / (co === 0 ? 0 : co)).toFixed(2);
  }
}
