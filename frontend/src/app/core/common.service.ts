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
  private allProflUrl = 'http://localhost:8080/listProf';
  private specfListlUrl = 'http://localhost:8080/listSpec';


  getListSemester(): Observable<Semester[]> {
    return this.http.get<Semester[]>(this.urlListSemester);
  }

  editSemester(sem): Observable<Semester> {
    return this.http.post<Semester>(this.urlListSemester, sem);
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

  public getAllProf(): Observable<ProfInfo[]> {
    return this.http.get<ProfInfo[]>(this.allProflUrl);
  }
  /**
   * getListSpec
   */
  public getListSpec(): Observable<Specialize[]> {
    return this.http.get<Specialize[]>(this.specfListlUrl);
  }

  public getSpecNameById(id) {
    return new Observable<String>(obs => {
      this.getListSpec().subscribe(data => {
        obs.next(data.find(spec => spec.idSpecialize === id).name);
      });
    });
  }
}
