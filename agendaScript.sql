create database agenda;

use agenda;

grant all on agenda.* to 'db_admin'@'localhost' identified by 'db_admin';

create table users  (
	user_id int(6) auto_increment,
	name varchar(25) not null comment "nombre para el usuario",
    surname varchar(25) not null comment "Apellido del usuario",
	email   varchar(60) not null comment "Correo electronico usado para entrar a la aplicacion",
	password varchar(40) not null  comment "campo de clave que es encriptada en la aplicacion.",
	status varchar(1) default 'A'  comment "Campo que representa el estado del usuario A=Activo, I=Inactivo",
	role varchar(20) not null comment "Campo con roles para el uso de la aplicacion",
    init_date datetime default current_timestamp comment "fecha en que fue creado el usuario",
	unique(email),
	primary key(user_id),
	check(status in ('A','I')),
	check(role in ('ADMIN','PARTICIPANTE'))
) comment="Contiene los usuarios del sistema.";

create table contacto (
	id int(6) auto_increment primary key,
	name varchar(60) not null,
	surname varchar(60) not null, 
	phone  varchar(60) not null, 
	email   varchar(60),
	user_id int(6) not null,
	foreign key (user_id) references users(user_id)
);

create table reasons (
	id int(6) primary key auto_increment,
	description varchar(200) not null
) comment "TBL con las razones por las cuales una persona cerraria su cuenta."; 

create table closes_accounts (
	id int(6) auto_increment primary key, 
	reason_id int(6) not null, 
	user_id int(6) not null, 
	close_date datetime default current_timestamp,
	feedback varchar(20000) not null,
	foreign key (reason_id) references reasons(id),
	foreign key (user_id) references users(user_id)
) comment "TBL con las cancelaciones de cuentas y las razones por las cuales fueron cerradas.";

insert into users values (null, 'Armando','Torres','ing.armandotorres@gmail.com','admin','A','ADMIN',current_timestamp);
insert into users values (null, 'Juan','Torres','Juan@gmail.com','juan','A','PARTICIPANTE',current_timestamp);

insert into reasons values (null,'No me agrada la pagina.');
insert into reasons values (null,'No funciona en algunos aspectos.');
insert into reasons values (null,'No me agrada el diseño.');
insert into reasons values (null,'Otras razones.');

select count(*) from users