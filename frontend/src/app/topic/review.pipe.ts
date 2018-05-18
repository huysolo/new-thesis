import { Pipe, PipeTransform } from '@angular/core';
import { Review } from '../models/Review';
import { MatTableDataSource } from '@angular/material';

@Pipe({
  name: 'review'
})
export class ReviewPipe implements PipeTransform {

  transform(value: MatTableDataSource<Review>, args?: any): any {
    if (value != null) {
      if (args == 'prof') {
        return new MatTableDataSource(value.data.filter(data => data.idCouncil == null));
      } else if (args == 'council') {
        return new MatTableDataSource(value.data.filter(data => data.idCouncil != null));
      }
    }
    return null;
  }

}
