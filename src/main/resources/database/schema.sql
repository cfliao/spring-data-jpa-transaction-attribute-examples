CREATE TABLE usera
(
    id   int not null,
    name varchar(80) null,
    age  int null,
    constraint pk_userid primary key (id)
);

CREATE TABLE grade
(
    id   int not null,
    userid int not null,
    score  int null,
    constraint pk_gradeid primary key (id)
);