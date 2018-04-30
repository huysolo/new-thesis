create table chat_group
(
  time         timestamp default CURRENT_TIMESTAMP not null,
  id_topic_sem int                                 not null,
  id_user      int                                 not null,
  content      varchar(50)                         not null,
  primary key (time, id_user)
)
  engine = InnoDB;

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
  engine = InnoDB;

create index comment_task_task_id_task_fk
  on comment_task (id_task);

create index comment_task_user_id_user_fk
  on comment_task (id_user);

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
  engine = InnoDB;

create table file
(
  id_file     int                                 not null
    primary key,
  name        varchar(45)                         null,
  upload_date timestamp default CURRENT_TIMESTAMP null,
  id_user     varchar(45)                         null,
  id_task     int                                 null
)
  engine = InnoDB;

create table hibernate_sequence
(
  next_val bigint null
)
  engine = MyISAM;

create table join_per_meeting
(
  id_student int not null,
  id_meeting int not null,
  primary key (id_student, id_meeting)
)
  engine = InnoDB;

create index join_per_meeting_meeting_id_meeting_fk
  on join_per_meeting (id_meeting);

create table meeting
(
  id_meeting    int             not null
    primary key,
  status        int default '1' null,
  content       varchar(150)    not null,
  student_count int default '0' null,
  id_topic_sem  int             null,
  reason        varchar(45)     null,
  title         varchar(45)     null
)
  engine = InnoDB;

create index meeting_topic_per_semester_id_topic_semester_fk
  on meeting (id_topic_sem);

create table meeting_schelule
(
  id_meeting_schelule int             not null
    primary key,
  status              int default '0' null,
  note                varchar(45)     null,
  meeting_time        timestamp       null,
  id_meeting          int             null,
  location            varchar(45)     null
)
  engine = InnoDB;

create table notification
(
  id_notify   int auto_increment
    primary key,
  id_user     int                                 not null,
  create_date timestamp default CURRENT_TIMESTAMP not null,
  content     varchar(120)                        null,
  notify_type varchar(45)                         null,
  constraint id_notify_UNIQUE
  unique (id_notify)
)
  engine = InnoDB;

create table professor
(
  id_professor int         not null
    primary key,
  id_user      int         not null,
  degree       varchar(45) null,
  skills       varchar(45) null,
  constraint professor_id_professor_uindex
  unique (id_professor),
  constraint professor_id_user_uindex
  unique (id_user)
)
  engine = InnoDB;

create table review
(
  id_prof  int not null,
  id_topic int not null,
  primary key (id_prof, id_topic)
)
  engine = InnoDB;

create table semester
(
  semester_no      int                                     not null
    primary key,
  apply_open_date  timestamp default CURRENT_TIMESTAMP     null
  on update CURRENT_TIMESTAMP,
  apply_close_date timestamp default CURRENT_TIMESTAMP     null
  on update CURRENT_TIMESTAMP,
  end_date         timestamp default '0000-00-00 00:00:00' not null,
  start_date       timestamp default '0000-00-00 00:00:00' not null,
  review_date      timestamp                               null,
  close_date       timestamp                               null
)
  engine = InnoDB;

create table specialize
(
  id_specialize int         not null
    primary key,
  id_falcuty    int         not null,
  name          varchar(45) not null
)
  engine = InnoDB;

create table standard
(
  id_standard int not null
    primary key,
  st_name     int null,
  id_prof     int null,
  semester_no int null
)
  comment 'Standard for Professors'
  engine = InnoDB;

create index standard_professor_id_professor_fk
  on standard (id_prof);

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
  engine = InnoDB;

create table student_task
(
  id_task     int                                 not null,
  id_student  int                                 not null,
  archive     varchar(100)                        null,
  upload_date timestamp default CURRENT_TIMESTAMP null
  on update CURRENT_TIMESTAMP,
  primary key (id_student, id_task)
)
  comment 'Student On Each Task'
  engine = InnoDB;

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
  engine = InnoDB;

create index student_topic_sem_student_id_student_fk
  on student_topic_sem (id_student);

create table task
(
  id_task      int auto_increment
    primary key,
  title        varchar(150)                        not null,
  description  varchar(200)                        null,
  deadline     timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP,
  id_topic_sem int                                 null,
  pass         int                                 null,
  submit       int                                 null
)
  engine = InnoDB;

create index task_topic_per_semester_id_topic_semester_fk
  on task (id_topic_sem);

create table topic
(
  id_top        int auto_increment
    primary key,
  title         varchar(150)                        not null,
  st_num_limit  int default '0'                     not null,
  sumary        varchar(300)                        null,
  id_prof       int                                 not null,
  score         int(10) default '0'                 null,
  semester_no   int(10)                             null,
  id_specialize int                                 null,
  upload_date   timestamp default CURRENT_TIMESTAMP null,
  publish_date  timestamp                           null,
  student_count int default '0'                     null,
  constraint topic_id_top_uindex
  unique (id_top)
)
  engine = InnoDB;

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
  engine = InnoDB;

create table topic_per_semester
(
  id_topic_semester int auto_increment
    primary key,
  score             int default '0' not null,
  semester_no       int             null,
  id_topic          int             not null,
  constraint topic_per_semester_id_topic_semester_uindex
  unique (id_topic_semester),
  constraint topic_per_semester_id_topic_uindex
  unique (id_topic)
)
  comment 'Topic on each semester'
  engine = InnoDB;

create table topic_requirement
(
  id_req   int auto_increment
    primary key,
  id_topic int         not null,
  detail   varchar(50) null,
  constraint topic_requirement_req_id_uindex
  unique (id_req)
)
  engine = InnoDB;

create table topic_sem_standard
(
  id_topic_sem int             not null,
  id_standard  int             not null,
  score        int default '0' not null,
  primary key (id_standard, id_topic_sem)
)
  comment 'Standard foreach topic per semester'
  engine = InnoDB;

create index topic_sem_standard_topic_per_semester_id_topic_semester_fk
  on topic_sem_standard (id_topic_sem);

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
  constraint user_id_user_uindex
  unique (id_user),
  constraint user_user_name_uindex
  unique (user_name),
  constraint user_email_uindex
  unique (email)
)
  engine = InnoDB;


