drop table if exists diary;
drop table if exists user;

create table diary (
    id bigint auto_increment,
    content text not null,
    created_by timestamp,
    place varchar(255) not null,
    sometime timestamp not null,
    title varchar(30) not null,
    with_who varchar(255) not null,
    image_url varchar(255),
    user_seq bigint not null,
    culture_id bigint not null,
    primary key (id)
);
create table user (seq bigint auto_increment, created_by timestamp, user_id bigint not null, user_name varchar(255), primary key (seq));

alter table user add constraint UK_USERID unique (user_id);
alter table diary add constraint DIARY_USER_FK foreign key (user_seq) references user (seq);
alter table diary add constraint DIARY_CULTURE_FK foreign key (culture_id) references culture (id);