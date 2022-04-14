drop table if exist person_jpa;
drop sequence if exist hibernate_sequence;
create table person_jpa ( id bigint not null, age integer not null,
first_name varchar(255) not null, integer integer, last_name varchar(255),
primary key (id));