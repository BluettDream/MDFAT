-- test_suite
drop table if exists test_suite;
create table test_suite
(
    id          integer                                       not null
        constraint test_suite_pk
            primary key autoincrement,
    name        varchar(64)                                   not null,
    run_time    integer  default -1                           not null,
    timeout     integer  default -1                           not null,
    description text,
    update_dt   DATETIME default (datetime('now', '+8 hour')) not null,
    create_dt   DATETIME default (datetime('now', '+8 hour')) not null
);

-- test_case
drop table if exists test_case;
create table test_case
(
    id          integer                                          not null
        constraint test_case_pk
            primary key autoincrement,
    suite_id    integer     default -1                           not null,
    next_id     integer     default -1                           not null,
    name        varchar(64) default ''                           not null,
    description TEXT,
    priority    integer     default -1                           not null,
    timeout     integer     default -1                           not null,
    run_time    integer     default -1                           not null,
    status      varchar(16) default 'NORMAL'                     not null,
    update_dt   DATETIME    default (datetime('now', '+8 hour')) not null,
    create_dt   DATETIME    default (datetime('now', '+8 hour')) not null
);

create index `idx_suite_next`
    on test_case (suite_id, next_id);

-- operation
drop table if exists operation;
create table operation
(
    id        integer                                       not null
        constraint operation_pk
            primary key autoincrement,
    case_id   integer                                       not null,
    operate   varchar(16)                                   not null,
    update_dt DATETIME default (datetime('now', '+8 hour')) not null,
    create_dt DATETIME default (datetime('now', '+8 hour')) not null
);

create index `idx_operation_case`
    on operation (case_id);

-- input_operation
drop table if exists input_operation;
create table input_operation
(
    id           integer      not null
        constraint input_operation_pk
            primary key autoincrement,
    operation_id integer      not null,
    value        varchar(128) not null
);

create unique index `uniq_operation`
    on input_operation (operation_id);

-- test_image
drop table if exists test_image;
create table test_image
(
    id         integer                                            not null
        constraint test_image_pk
            primary key autoincrement,
    case_id    integer       default -1                           not null,
    link       varchar(128)                                       not null,
    confidence decimal(5, 2) default 0.0                          not null,
    x          integer       default 0                            not null,
    y          integer       default 0                            not null,
    width      integer                                            not null,
    height     integer                                            not null,
    update_dt  DATETIME      default (datetime('now', '+8 hour')) not null,
    create_dt  DATETIME      default (datetime('now', '+8 hour')) not null
);

create index `idx_image_case`
    on test_image (case_id);

-- test_text
drop table if exists test_text;
create table test_text
(
    id         integer                                             not null
        constraint test_text_pk
            primary key autoincrement,
    case_id    integer        default -1                           not null,
    text       TEXT                                                not null,
    confidence decimal(10, 2) default 0.0                          not null,
    x          integer        default 0                            not null,
    y          integer        default 0                            not null,
    update_dt  DATETIME       default (datetime('now', '+8 hour')) not null,
    create_dt  DATETIME       default (datetime('now', '+8 hour')) not null
);

create index `idx_text_case`
    on test_text (case_id);

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

create unique index `uniq_setting`
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

DROP TRIGGER IF EXISTS update_operation_dt;
CREATE TRIGGER update_operation_dt
    AFTER UPDATE
    ON operation
    FOR EACH ROW
BEGIN
    UPDATE operation SET update_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
END;

DROP TRIGGER IF EXISTS insert_operation_dt;
CREATE TRIGGER insert_operation_dt
    AFTER INSERT
    ON operation
    FOR EACH ROW
BEGIN
    UPDATE operation SET create_dt = datetime('now', '+8 hour') WHERE id = NEW.id;
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