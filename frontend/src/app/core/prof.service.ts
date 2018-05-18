import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { ProfInfo } from '../models/ProfInfo';

@Injectable()
export class ProfService {

  constructor(private commonSv: CommonService) { }

  profList: ProfInfo[];

  init() {
    this.commonSv.getListProf().subscribe(data => {
      this.profList = data;
    });
  }

}
