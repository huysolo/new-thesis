import {Injectable, NgZone} from '@angular/core';
import {HttpClient, HttpRequest, HttpEvent, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UploadFileService {
  private newverUrl = 'http://localhost:8080/addversion';
  private fileUrl = 'http://localhost:8080/file';

  constructor(private http: HttpClient, private zone: NgZone) {}

  pushFileToStorage(file: File, id, ver, isGeneral): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('file', file);
    formdata.append('id', id);
    formdata.append('general', isGeneral);

    const req = new HttpRequest('POST', 'http://localhost:8080/post', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  newVersion(file: File, id, isGeneral): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('file', file);
    formdata.append('id', id);
    formdata.append('general', isGeneral);

    const req = new HttpRequest('POST', 'http://localhost:8080/addversion', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  // newVersion(taskId) {
  //   return this.http.post<any>(this.newverUrl, taskId);
  // }

  getFiles(taskId, version, idUser): Observable<string[]> {
    let params = new HttpParams().append('id', taskId);
    if (version != null) {
      params = params.append('ver', version);
    }
    if (idUser != null) {
      params = params.append('idUser', idUser);
    }
    return this.http.get<string[]>('http://localhost:8080/getallfiles', {params: params});
  }

  getListVersion(taskVersion: number): Observable<number[]> {
    return new Observable(obs => {
      const verLst: number[] = new Array<number>();
      for (let i = taskVersion; i >= 0; i = i - 1) {
        verLst.push(i);
      }
      obs.next(verLst);
    });
  }

  deleteFile(idTask, version, name, general) {
    const params = new HttpParams().append('name', name).append('ver', version).append('id', idTask).append('general', general);
    return this.http.delete<any>(this.fileUrl, {params: params});
  }
}
