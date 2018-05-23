import {StudentMeeting} from './student-meeting';
import {TimeLocation} from './time-location';

export class Meeting {
    title: String;
    meetingID: number;
    content: String;
    reason: String;
    topicID: number;
    status: number;
    reportContent: String;
    reportPlan: String;
    student: Array<StudentMeeting>;
    timeLocation: Array<TimeLocation>;
    constructor(){
        this.student = [];
        this.timeLocation = [];
    }
}

