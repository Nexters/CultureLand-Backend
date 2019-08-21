/*drop table if exists culture_rowdata ;
drop table if exists culture;*/

CREATE TABLE if not exists culture (id bigint auto_increment, culture_name varchar(255), primary key (id), constraint Uniquekey_constraint unique(culture_name));
create table if not exists culture_rowdata (id bigint auto_increment, start_date varchar(255), end_date varchar(255), image_url varchar(255), place varchar(255), title varchar(255),
popular bigint, culture_id bigint, primary key(id), constraint rawdata_key_constraint foreign key (culture_id) references culture(id));

/*alter table culture add constraint Uniquekey_constraint unique (culture_name);
alter table culture_rowdata add constraint rawdata_key_constraint foreign key (culture_id) references culture(id);*/
