drop table if exists person CASCADE;
create table person 
(
	person_id integer not null auto_increment,
	age integer,
	date_of_birth date,
	email varchar(255),
	password varchar(255),
	person_name varchar(255), 
	person_surname varchar(255),
	phone_number varchar(255),
	username varchar(255),
	primary key (person_id)
);