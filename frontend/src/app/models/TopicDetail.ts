import { Topic } from './Topic';
import { TopicMission } from './TopicMission';
import { TopicRequirement } from './TopicRequirement';

export class TopicDetail {
  topic: Topic;
  topicMission: TopicMission[];
  topicRequirement: TopicRequirement[];
  draft: Boolean;
  constructor() {
    this.topic = new Topic();
    this.topicMission = [];
    this.topicRequirement = [new TopicRequirement()];
    this.draft = false;
  }

}
