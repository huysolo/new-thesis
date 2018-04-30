import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpEvent, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UploadFileService {

  constructor(private http: HttpClient) {}

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('file', file);
    formdata.append('id', '1');

    const req = new HttpRequest('POST', 'http://localhost:8080/post', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<string[]> {
    const params = new HttpParams().append('id', '1');
    return this.http.get<string[]>('http://localhost:8080/getallfiles', {params: params});
  }
}
