drop table if exist personjpa;
drop sequence if exist personjpa_sequence;
create sequence personjpa_sequence;
create table personjpa ( id bigint not null default personjpa_sequence.nextval, age integer not null,
first_name varchar(255) not null, integer integer, last_name varchar(255) not null,
primary key (id));