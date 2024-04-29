-- test_suite
drop table if exists test_suite;
create table test_suite
(
    id          integer                                          not null
        constraint test_suite_pk
            primary key autoincrement,
    name        varchar(64)                                      not null,
    run_time    integer     default 0                            not null,
    timeout     integer     default 0                            not null,
    status      varchar(16) default 'READY'                      not null,
    description text,
    update_dt   DATETIME    default (datetime('now', '+8 hour')) not null,
    create_dt   DATETIME    default (datetime('now', '+8 hour')) not null
);

-- test_suite_extend
drop table if exists test_suite_relation;
create table test_suite_relation
(
    id       integer           not null
        constraint test_suite_relation_pk
            primary key autoincrement,
    suite_id integer default 0 not null,
    case_id  integer default 0 not null
);

create index `idx_suite_case`
    on test_suite_relation (suite_id, case_id);

-- test_case
drop table if exists test_case;
create table test_case
(
    id           integer                                          not null
        constraint test_case_pk
            primary key autoincrement,
    pre_case_id  integer     default -1,
    post_case_id integer     default -1,
    name         varchar(64) default ''                           not null,
    description  TEXT,
    priority     integer     default 0                            not null,
    operation    integer     default 0                            not null,
    timeout      integer     default 0                            not null,
    run_time     integer     default 0                            not null,
    status       varchar(16) default 'READY'                      not null,
    update_dt    DATETIME    default (datetime('now', '+8 hour')) not null,
    create_dt    DATETIME    default (datetime('now', '+8 hour')) not null
);

-- test_case_relation
drop table if exists test_case_relation;
create table test_case_relation
(
    id       integer           not null
        constraint test_case_relation_pk
            primary key autoincrement,
    case_id  integer default 0 not null,
    image_id integer default 0 not null,
    text_id  integer default 0 not null
);

create index `idx_case_image`
    on test_case_relation (case_id, image_id);

create index `idx_case_text`
    on test_case_relation (case_id, text_id);

-- test_image
drop table if exists test_image;
create table test_image
(
    id         integer                                             not null
        constraint test_image_pk
            primary key autoincrement,
    link       varchar(128)                                        not null,
    confidence decimal(10, 2) default 0.0                          not null,
    point      varchar(32)    default '[]'                         not null,
    width      integer                                             not null,
    height     integer                                             not null,
    update_dt  DATETIME       default (datetime('now', '+8 hour')) not null,
    create_dt  DATETIME       default (datetime('now', '+8 hour')) not null
);

-- test_text
drop table if exists test_text;
create table test_text
(
    id         integer                                             not null
        constraint test_text_pk
            primary key autoincrement,
    text       TEXT                                                not null,
    confidence decimal(10, 2) default 0.0                          not null,
    point      varchar(32)    default '[]'                         not null,
    update_dt  DATETIME       default (datetime('now', '+8 hour')) not null,
    create_dt  DATETIME       default (datetime('now', '+8 hour')) not null
);

-- setting
drop table if exists setting;
create table setting
(
    id          integer                                       not null
        constraint setting_pk
            primary key autoincrement,
    key         varchar(32)                                   not null,
    value       varchar(128)                                  not null,
    description TEXT,
    update_dt   DATETIME default (datetime('now', '+8 hour')) not null,
    create_dt   DATETIME default (datetime('now', '+8 hour')) not null
);

create index `idx_setting`
    on setting (key);

DROP TRIGGER IF EXISTS update_test_suite_dt;
CREATE TRIGGER update_test_suite_dt
    AFTER UPDATE
    ON test_suite
    FOR EACH ROW
BEGIN
    UPDATE test_suite SET update_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS insert_test_suite_dt;
CREATE TRIGGER insert_test_suite_dt
    AFTER INSERT
    ON test_suite
    FOR EACH ROW
BEGIN
    UPDATE test_suite SET create_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS update_test_case_dt;
CREATE TRIGGER update_test_case_dt
    AFTER UPDATE
    ON test_case
    FOR EACH ROW
BEGIN
    UPDATE test_case SET update_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS insert_test_case_dt;
CREATE TRIGGER insert_test_case_dt
    AFTER INSERT
    ON test_case
    FOR EACH ROW
BEGIN
    UPDATE test_case SET create_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS update_test_image_dt;
CREATE TRIGGER update_test_image_dt
    AFTER UPDATE
    ON test_image
    FOR EACH ROW
BEGIN
    UPDATE test_image SET update_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS insert_test_image_dt;
CREATE TRIGGER insert_test_image_dt
    AFTER INSERT
    ON test_image
    FOR EACH ROW
BEGIN
    UPDATE test_image SET create_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS update_test_text_dt;
CREATE TRIGGER update_test_text_dt
    AFTER UPDATE
    ON test_text
    FOR EACH ROW
BEGIN
    UPDATE test_text SET update_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS insert_test_text_dt;
CREATE TRIGGER insert_test_text_dt
    AFTER INSERT
    ON test_text
    FOR EACH ROW
BEGIN
    UPDATE test_text SET create_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS update_setting_dt;
CREATE TRIGGER update_setting_dt
    AFTER UPDATE
    ON setting
    FOR EACH ROW
BEGIN
    UPDATE setting SET update_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS insert_setting_dt;
CREATE TRIGGER insert_setting_dt
    AFTER INSERT
    ON setting
    FOR EACH ROW
BEGIN
    UPDATE setting SET create_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;