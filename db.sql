create table DELIVERY_SERVICE_REF
(
    ID      BIGINT auto_increment
        primary key,
    CODE    VARCHAR(255),
    CREATED TIMESTAMP,
    DETAIL  VARCHAR(255),
    UPDATED TIMESTAMP,
    VERSION INTEGER
);

create table TEENAGER
(
    ID          BIGINT auto_increment
        primary key,
    EMAIL       VARCHAR(255),
    FIRST_NAME  VARCHAR(255),
    LAST_NAME   VARCHAR(255),
    ADDRESS     VARCHAR(255),
    BIRTHDAY    DATE,
    CONTACT     VARCHAR(255),
    CREATED     TIMESTAMP,
    INN         VARCHAR(255),
    MIDDLE_NAME VARCHAR(255),
    SEX         VARCHAR(255),
    UPDATED     TIMESTAMP,
    VERSION     INTEGER
);

create table CARETAKER
(
    ID          BIGINT auto_increment
        primary key,
    ADDRESS     VARCHAR(255),
    BIRTHDAY    DATE,
    CONTACT     VARCHAR(255),
    CREATED     TIMESTAMP,
    EMAIL       VARCHAR(255),
    FIRST_NAME  VARCHAR(255),
    INN         VARCHAR(255),
    LAST_NAME   VARCHAR(255),
    MIDDLE_NAME VARCHAR(255),
    SEX         VARCHAR(255),
    TEENAGER_ID BIGINT,
    UPDATED     TIMESTAMP,
    VERSION     INTEGER,
    WORK        VARCHAR(255),
    constraint FKPWWNPTLEJA2DC3U9K3JJSXW5H
        foreign key (TEENAGER_ID) references TEENAGER (ID)
);

create table HARD_LIFE_REF
(
    ID      BIGINT auto_increment
        primary key,
    CODE    VARCHAR(255),
    CREATED TIMESTAMP,
    DETAIL  VARCHAR(255),
    UPDATED TIMESTAMP,
    VERSION INTEGER
);

create table PSYCHO_ACTIVE_REF
(
    ID      BIGINT auto_increment
        primary key,
    CODE    VARCHAR(255),
    CREATED TIMESTAMP,
    DETAIL  VARCHAR(255),
    UPDATED TIMESTAMP,
    VERSION INTEGER
);

create table SURVEY
(
    ID          BIGINT auto_increment
        primary key,
    CREATED     TIMESTAMP,
    UPDATED     TIMESTAMP,
    VERSION     INTEGER,
    TEENAGER_ID BIGINT,
    constraint FKF5DRKR0NV21EDBLVOFRJ555V7
        foreign key (TEENAGER_ID) references TEENAGER (ID)
);

create table SURVEY_REF
(
    ID           BIGINT auto_increment
        primary key,
    CODE         VARCHAR(255),
    CREATED      TIMESTAMP,
    DETAIL       VARCHAR(255),
    IS_BOOLEAN   VARCHAR(255),
    IS_NUMBER    VARCHAR(255),
    IS_REFERENCE VARCHAR(255),
    IS_STRING    VARCHAR(255),
    UPDATED      TIMESTAMP,
    VERSION      INTEGER
);

create table SURVEY_RESULT
(
    ID            BIGINT auto_increment
        primary key,
    CREATED       TIMESTAMP,
    UPDATED       TIMESTAMP,
    VALUE         VARCHAR(255),
    VERSION       INTEGER,
    SURVEY_ID     BIGINT,
    SURVEY_REF_ID BIGINT,
    constraint FKAFM22XWSGQGX3NJAVGT9ENFKY
        foreign key (SURVEY_REF_ID) references SURVEY_REF (ID),
    constraint FKRAW6C6GM9E9IN5CWQS1LLQV5L
        foreign key (SURVEY_ID) references SURVEY (ID)
);

create table REQUEST
(
    ID                  BIGINT auto_increment
        primary key,
    DATE                TIMESTAMP,
    NUM                 VARCHAR(255),
    TEENAGER_ID         BIGINT,
    DELIVERY_SERVICE_ID BIGINT,
    HARD_LIFE_ID        BIGINT,
    PSYCHO_ACTIVE_ID    BIGINT,
    ADDICTION           VARCHAR(max),
    CONSULTATION        VARCHAR(max),
    CREATED             TIMESTAMP,
    DEVIATION           VARCHAR(max),
    SUPPORT             VARCHAR(max),
    UPDATED             TIMESTAMP,
    VERSION             INTEGER,
    constraint FK8TOA9R0CG4QHF22D1IACLX5RA
        foreign key (HARD_LIFE_ID) references HARD_LIFE_REF (ID),
    constraint FKB4T4E734E3HYK9AXJP1XWBUL6
        foreign key (TEENAGER_ID) references TEENAGER (ID),
    constraint FKP2EXFST6T2YVFIW8XW1A59E46
        foreign key (DELIVERY_SERVICE_ID) references DELIVERY_SERVICE_REF (ID),
    constraint FKRYAPD5TQLIIP766GUYTHCTFYX
        foreign key (PSYCHO_ACTIVE_ID) references PSYCHO_ACTIVE_REF (ID)
);

