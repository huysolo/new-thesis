import {StudentMeeting} from './student-meeting';
import {TimeLocation} from './time-location';

export class Meeting {
    content: String;
    note: String;
    topicid: number;
    stautus: number;
    student: Array<StudentMeeting>;
    timelocation: Array<TimeLocation>;
    constructor(){
        this.student = [];
        this.timelocation = [];
    }
}

