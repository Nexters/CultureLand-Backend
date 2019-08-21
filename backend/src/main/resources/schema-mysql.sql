drop table if exists diary;
drop table if exists wishList;
drop table if exists user;
drop table if exists culture_rowdata ;
drop table if exists culture;

CREATE TABLE culture (id bigint auto_increment, culture_name varchar(255), primary key (id));
create table culture_rowdata (id bigint auto_increment, start_date varchar(255), end_date varchar(255), image_url varchar(255), place varchar(255), title varchar(255), culture_id bigint, primary key (id));

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

create table wishList (
    id bigint auto_increment,
    culture_rowdata_id bigint not null,
    user_seq bigint not null,
    primary key (id)
);

alter table culture add constraint Uniquekey_constraint unique (culture_name);
alter table culture_rowdata add constraint rawdata_key_constraint foreign key (culture_id) references culture(id);
alter table user add constraint UK_USERID unique (user_id);
alter table diary add constraint DIARY_USER_FK foreign key (user_seq) references user (seq);
alter table wishList add constraint WISHLIST_USER_FK foreign key(user_seq) references user (seq);
alter table wishList add constraint WISHLIST_CULTURE_FK foreign key(culture_rowdata_id) references culture_rowdata (id);
alter table diary add constraint DIARY_CULTURE_FK foreign key (culture_id) references culture (id);
