drop table if exists test_case;
create table test_case
(
    id          integer                                       not null
        constraint test_case_pk
            primary key autoincrement,
    suite_id    integer                                       not null,
    name        TEXT                                          not null,
    priority    integer  default 0                            not null,
    operation   integer  default 0                            not null,
    run_time    integer  default 0                            not null,
    timeout     integer  default 0                            not null,
    status      integer  default 0                            not null,
    description TEXT,
    update_time DATETIME default (datetime('now', '+8 hour')) not null,
    create_time DATETIME default (datetime('now', '+8 hour')) not null
);

create index test_case_id_suite_id_index
    on test_case (id, suite_id);

create index test_case_suite_id_index
    on test_case (suite_id);

drop table if exists test_image;
create table test_image
(
    id          integer                                       not null
        constraint test_image_pk
            primary key autoincrement,
    case_id     integer                                       not null,
    path        TEXT                                          not null,
    confidence  REAL     default 0.0                          not null,
    point_x     INTEGER,
    point_y     INTEGER,
    width       integer                                       not null,
    height      integer                                       not null,
    update_time DATETIME default (datetime('now', '+8 hour')) not null,
    create_time DATETIME default (datetime('now', '+8 hour')) not null
);

create index test_image_case_id_index
    on test_image (case_id);

create index test_image_id_case_id_index
    on test_image (id, case_id);

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

drop table if exists test_text;
create table test_text
(
    id          integer                                       not null
        constraint test_text_pk
            primary key autoincrement,
    case_id     integer                                       not null,
    text        TEXT                                          not null,
    confidence  REAL     default 0.0                          not null,
    point_x     integer,
    point_y     integer,
    update_time DATETIME default (datetime('now', '+8 hour')) not null,
    create_time DATETIME default (datetime('now', '+8 hour')) not null
);

create index test_text_case_id_index
    on test_text (case_id);

create index test_text_id_case_id_index
    on test_text (id, case_id);

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