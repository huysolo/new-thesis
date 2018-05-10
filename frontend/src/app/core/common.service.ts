import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Semester } from '../models/Semester';

import { ProfInfo } from '../models/ProfInfo';
import { Specialize } from '../models/Specialize';

@Injectable()
export class CommonService {

  constructor(private http: HttpClient) { }

  private urlListSemester = 'http://localhost:8080/listSemester';
  private urlAllSem = 'http://localhost:8080/allSemNo';
  private profListlUrl = 'http://localhost:8080/listProf';
  private specfListlUrl = 'http://localhost:8080/listSpec';


  getListSemester(): Observable<Semester[]> {
    return this.http.get<Semester[]>(this.urlListSemester);
  }

  getAllSemNo(): Observable<Number[]> {
    return this.http.get<Number[]>(this.urlAllSem);
  }

  /**
   * getListProf
   */
  public getListProf(): Observable<ProfInfo[]> {
    return this.http.get<ProfInfo[]>(this.profListlUrl);
  }

  /**
   * getListSpec
   */
  public getListSpec(): Observable<Specialize[]> {
    return this.http.get<Specialize[]>(this.specfListlUrl);
  }
}
