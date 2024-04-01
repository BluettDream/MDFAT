-- test_suite
drop table if exists test_suite;
create table test_suite
(
    id          integer                                       not null
        constraint test_suite_pk
            primary key autoincrement,
    name        text                                          not null,
    run_time    integer  default 0                            not null,
    timeout     integer  default 0                            not null,
    status      integer  default 0                            not null,
    description text,
    update_time DATETIME default (datetime('now', '+8 hour')) not null,
    create_time DATETIME default (datetime('now', '+8 hour')) not null
);

-- test_suite_extend
drop table if exists test_suite_extend;
create table test_suite_extend
(
    id       integer           not null
        constraint test_suite_extend_pk
            primary key autoincrement,
    suite_id integer default 0 not null,
    case_id  integer default 0 not null
);

create index test_suite_extend_case_id_index
    on test_suite_extend (suite_id, case_id);

-- test_case
drop table if exists test_case;
create table test_case
(
    id          integer                                       not null
        constraint test_case_pk
            primary key autoincrement,
    name        TEXT                                          not null,
    priority    integer  default 0                            not null,
    operation   integer  default 0                            not null,
    timeout     integer  default 0                            not null,
    run_time    integer  default 0                            not null,
    status      integer  default 0                            not null,
    description TEXT,
    update_time DATETIME default (datetime('now', '+8 hour')) not null,
    create_time DATETIME default (datetime('now', '+8 hour')) not null
);

-- test_case_extend
drop table if exists test_case_extend;
create table test_case_extend
(
    id       integer           not null
        constraint test_case_extend_pk
            primary key autoincrement,
    case_id  integer default 0 not null,
    image_id integer default 0 not null,
    text_id  integer default 0 not null,
    pre_case_id integer default 0 ,
    post_case_id integer default 0
);

create index test_case_extend_image_id_index
    on test_case_extend (case_id, image_id);

create index test_case_extend_text_id_index
    on test_case_extend (case_id, text_id);

-- test_image
drop table if exists test_image;
create table test_image
(
    id          integer                                       not null
        constraint test_image_pk
            primary key autoincrement,
    path        TEXT                                          not null,
    confidence  REAL     default 0.0                          not null,
    point_x     INTEGER,
    point_y     INTEGER,
    width       integer                                       not null,
    height      integer                                       not null,
    update_time DATETIME default (datetime('now', '+8 hour')) not null,
    create_time DATETIME default (datetime('now', '+8 hour')) not null
);

-- test_text
drop table if exists test_text;
create table test_text
(
    id          integer                                       not null
        constraint test_text_pk
            primary key autoincrement,
    text        TEXT                                          not null,
    confidence  REAL     default 0.0                          not null,
    point_x     integer,
    point_y     integer,
    update_time DATETIME default (datetime('now', '+8 hour')) not null,
    create_time DATETIME default (datetime('now', '+8 hour')) not null
);

-- settings
drop table if exists settings;
create table settings
(
    id          integer                                       not null
        constraint settings_pk
            primary key autoincrement,
    key         TEXT                                          not null,
    value       TEXT                                          not null,
    description TEXT,
    update_time DATETIME default (datetime('now', '+8 hour')) not null,
    create_time DATETIME default (datetime('now', '+8 hour')) not null
);