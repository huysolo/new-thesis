create table chat_group
(
  time         timestamp default CURRENT_TIMESTAMP not null,
  id_topic_sem int                                 not null,
  id_user      int                                 not null,
  content      varchar(50)                         not null,
  primary key (time, id_user)
)
  charset = utf8mb4;

create table comment_task
(
  time    timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP,
  id_task int                                 not null,
  id_user int                                 not null,
  content varchar(150)                        not null,
  primary key (time, id_task, id_user)
)
  comment 'Comment on each task'
  charset = utf8mb4;

create index comment_task_task_id_task_fk
  on comment_task (id_task);

create index comment_task_user_id_user_fk
  on comment_task (id_user);

create table council
(
  id_council   int auto_increment
    primary key,
  create_date  timestamp default CURRENT_TIMESTAMP not null,
  council_name varchar(45)                         not null
);

create table council_user
(
  id_council int             not null,
  id_user    int             not null,
  role       int default '0' not null
  comment '0: member',
  primary key (id_council, id_user)
);

create table faculty
(
  id_faculty int auto_increment
    primary key,
  name       varchar(45) not null,
  constraint faculty_id_faculty_uindex
  unique (id_faculty),
  constraint faculty_name_uindex
  unique (name)
)
  charset = utf8mb4;

create table file
(
  id_file     int auto_increment
    primary key,
  name        varchar(45)                         null,
  upload_date timestamp default CURRENT_TIMESTAMP null
  on update CURRENT_TIMESTAMP,
  id_user     int                                 null,
  id_task     int                                 null,
  version     int default '0'                     null
)
  charset = utf8mb4;

create table general_standard
(
  id_general_standard int auto_increment
    primary key,
  stName              varchar(50)  not null,
  coefficient         int unsigned not null,
  semester_no         int          not null,
  st_name             varchar(255) null
)
  charset = utf8mb4;

create table hibernate_sequence
(
  next_val bigint null
)
  engine = MyISAM
  charset = utf8mb4;

create table join_per_meeting
(
  id_student    int          not null,
  id_meeting    int          not null,
  diary_content varchar(255) null,
  diary_plan    varchar(255) null,
  primary key (id_student, id_meeting)
)
  charset = utf8mb4;

create index join_per_meeting_meeting_id_meeting_fk
  on join_per_meeting (id_meeting);

create table meeting
(
  id_meeting    int auto_increment
    primary key,
  status        int default '1' null,
  content       varchar(150)    not null,
  student_count int default '0' null,
  id_topic_sem  int             null,
  reason        varchar(45)     null,
  title         varchar(45)     null
)
  charset = utf8mb4;

create index meeting_topic_per_semester_id_topic_semester_fk
  on meeting (id_topic_sem);

create table meeting_schelule
(
  status       int default '0' null,
  meeting_time timestamp       not null,
  id_meeting   int             not null,
  location     varchar(45)     not null,
  primary key (meeting_time, id_meeting, location)
)
  charset = utf8mb4;

create table notification
(
  id_notify   int auto_increment
    primary key,
  id_user     int                                 not null,
  create_date timestamp default CURRENT_TIMESTAMP not null,
  content     varchar(120)                        null,
  notify_type varchar(45)                         null,
  id_content  int                                 null,
  constraint id_notify_UNIQUE
  unique (id_notify)
)
  charset = utf8mb4;

create table professor
(
  id_professor int auto_increment
    primary key,
  id_user      int         not null,
  degree       varchar(45) null,
  skills       varchar(45) null
)
  charset = utf8mb4;

create table review
(
  id_prof    int             null,
  id_topic   int             not null,
  score      float           null,
  submitted  int default '0' not null,
  id_review  int auto_increment
    primary key,
  id_council int             null
)
  charset = utf8mb4;

create table semester
(
  semester_no         int                                     not null
    primary key,
  begin_date          timestamp                               null,
  apply_open_date     timestamp                               null
  on update CURRENT_TIMESTAMP,
  start_date          timestamp default '0000-00-00 00:00:00' not null,
  midterm_review_date timestamp                               null,
  review_date         timestamp                               null,
  close_date          timestamp                               null,
  apply_close_date    timestamp                               null,
  end_date            timestamp default '0000-00-00 00:00:00' not null
)
  charset = utf8mb4;

create table specialize
(
  id_specialize int auto_increment
    primary key,
  id_falcuty    int         not null,
  name          varchar(45) not null
)
  charset = utf8mb4;

create table standard
(
  id_standard int auto_increment
    primary key,
  st_name     varchar(50)  not null,
  id_user     int          null,
  coefficient int unsigned not null,
  semester_no int          null
)
  comment 'Standard for Professors'
  charset = utf8mb4;

create table student
(
  id_student int not null
    primary key,
  id_user    int not null,
  constraint id_student_UNIQUE
  unique (id_student),
  constraint student_id_user_uindex
  unique (id_user)
)
  charset = utf8mb4;

create table student_task
(
  id_task         int                                 not null,
  id_student      int                                 not null,
  archive         varchar(100)                        null,
  upload_date     timestamp default CURRENT_TIMESTAMP null
  on update CURRENT_TIMESTAMP,
  current_version int default '0'                     null,
  primary key (id_student, id_task)
)
  comment 'Student On Each Task'
  charset = utf8mb4;

create table student_topic_sem
(
  id_student   int             not null,
  id_topic_sem int             not null,
  team_lead    int default '0' not null
  comment 'team lead: 1
		other member 0',
  primary key (id_topic_sem, id_student)
)
  comment 'List of student belong to each topic per semester'
  charset = utf8mb4;

create index student_topic_sem_student_id_student_fk
  on student_topic_sem (id_student);

create table task
(
  id_task         int auto_increment
    primary key,
  title           varchar(150)                        not null,
  description     varchar(200)                        null,
  deadline        timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP,
  id_topic_sem    int                                 null,
  pass            int                                 null,
  submit          int                                 null,
  current_version int default '0'                     null,
  update_time     timestamp default CURRENT_TIMESTAMP null
  on update CURRENT_TIMESTAMP
)
  charset = utf8mb4;

create index task_topic_per_semester_id_topic_semester_fk
  on task (id_topic_sem);

create table topic
(
  id_top        int auto_increment
    primary key,
  title         varchar(200)                        not null,
  st_num_limit  int default '0'                     not null,
  sumary        varchar(300)                        null,
  id_prof       int                                 not null,
  score         int(10) default '0'                 null,
  semester_no   int(10)                             null,
  id_specialize int                                 null,
  upload_date   timestamp default CURRENT_TIMESTAMP null
  on update CURRENT_TIMESTAMP,
  publish_date  timestamp                           null,
  student_count int default '0'                     null,
  prof_score    int                                 null,
  review_date   datetime                            null,
  constraint topic_id_top_uindex
  unique (id_top)
)
  charset = utf8mb4;

create index topic_professor_id_professor_fk
  on topic (id_prof);

create table topic_mission
(
  id_mission int auto_increment
    primary key,
  id_topic   int         not null,
  detail     varchar(60) not null,
  constraint mission_topic_mission_id_uindex
  unique (id_mission)
)
  charset = utf8mb4;

create table topic_per_semester
(
  id_review int auto_increment,
  score     int default '0' not null,
  title     int             not null,
  primary key (id_review, title),
  constraint topic_per_semester_id_topic_semester_uindex
  unique (id_review)
)
  comment 'Topic on each semester'
  charset = utf8mb4;

create table topic_requirement
(
  id_req   int auto_increment
    primary key,
  id_topic int          not null,
  detail   varchar(200) null,
  constraint topic_requirement_req_id_uindex
  unique (id_req)
)
  charset = utf8mb4;

create table topic_sem_standard
(
  id_topic_sem_standard int auto_increment
    primary key,
  content               varchar(45)              not null,
  coefficient           int default '1'          not null,
  id_review             int                      not null,
  score                 int unsigned default '0' not null
)
  comment 'Standard foreach topic per semester'
  charset = utf8mb4;

create table user
(
  id_user    int auto_increment
    primary key,
  user_name  varchar(50) not null,
  password   varchar(50) not null,
  first_name varchar(50) null,
  last_name  varchar(50) null,
  email      varchar(50) null,
  photo      varchar(45) null,
  gender     varchar(45) not null
  comment '1: male
	0: female',
  id_falcuty int         null,
  role       int         null,
  constraint user_id_user_uindex
  unique (id_user),
  constraint user_user_name_uindex
  unique (user_name),
  constraint user_email_uindex
  unique (email)
)
  charset = utf8mb4;


