drop table if exists diary;
drop table if exists dibs;
drop table if exists user;

create table diary (
    id bigint auto_increment,
    content text not null,
    created_by timestamp,
    place varchar(255) not null,
    sometime date not null,
    title varchar(30) not null,
    with_who varchar(255) not null,
    image_url varchar(255),
    favorite bit(1),
    user_seq bigint not null,
    culture_id bigint not null,
    primary key (id)
);

create table user (
    seq bigint auto_increment,
    created_by timestamp,
    user_id bigint not null,
    user_name varchar(255),
    primary key (seq)
);

create table dibs (
    id bigint auto_increment,
    start_date varchar(255),
    end_date varchar(255),
    image_url varchar(255),
    place varchar(255),
    title varchar(255),
    user_seq bigint not null,
    primary key (id)
);

alter table user add constraint UK_USERID unique (user_id);

alter table diary add constraint DIARY_USER_FK foreign key (user_seq) references user (seq);

alter table dibs add constraint DIBS_USER_FK foreign key(user_seq) references user (seq);
alter table diary add constraint DIARY_CULTURE_FK foreign key (culture_id) references culture (id);
