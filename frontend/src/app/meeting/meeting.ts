import {StudentMeeting} from './student-meeting';
import {TimeLocation} from './time-location';

export class Meeting {
    title: String;
    meetingid: String;
    content: String;
    reason: String;
    topicID: number;
    status: number;
    student: Array<StudentMeeting>;
    timeLocation: Array<TimeLocation>;
    constructor(){
        this.student = [];
        this.timeLocation = [];
    }
}

