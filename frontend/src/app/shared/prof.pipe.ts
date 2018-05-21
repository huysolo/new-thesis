import { Pipe, PipeTransform } from '@angular/core';
import { ProfService } from '../core/prof.service';

@Pipe({
  name: 'prof'
})
export class ProfPipe implements PipeTransform {
  constructor(private profSv: ProfService) {}

  transform(value: number, args?: any): any {
    if (args == 'id') {
      return this.profSv.profList.find(prof => prof.professor.idProfessor == value).userId;
    }
    return this.profSv.profList.find(prof => prof.professor.idProfessor == value).name;
  }

}
