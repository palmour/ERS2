/*DROP database if it exists*/
DROP user ers Cascade;

/*Create the database user*/
Create user ers
Identified by ers
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M on users;

grant connect to ers;
grant resource to ers;
grant create session to ers;
grant create table to ers;
grant create view to ers;

connect ers/ers;

/*Create tables*/
create table ERS_USERS(
	U_ID number(*,0) not null,
	U_USERNAME varchar2(40) not null,
	U_PASSWORD varchar2(40) not null,
	U_FIRSTNAME varchar2(30),
	U_LASTNAME varchar2(30),
	U_EMAIL varchar(100),
	UR_ID number(*,0) default 3 not null,
	Constraint ERS_USERS_PK primary key (U_ID),
	Constraint ERS_USERS_U_USERNAME_UN unique (U_USERNAME),
	Constraint ERS_USERS_U_EMAIL_UN unique (U_EMAIL)
);
create table ERS_USER_ROLES(
	UR_ID number(*,0) not null,
	UR_ROLE varchar2(40),
	Constraint ERS_USER_ROLES_PK primary key (UR_ID)
);
create table ERS_REIMBURSEMENTS(
	R_ID number(*,0) not null,
	R_AMOUNT number(22,0),
	R_DESCRIPTION varchar2(100),
	R_RECEIPT BLOB,
	R_SUBMITTED Timestamp not null,
	R_RESOLVED timestamp,
	U_ID_AUTHOR number(*,0) not null,
	U_ID_RESOLVER number(*,0),
	RT_TYPE  number(*,0) not null,
	RT_STATUS number(*,0) not null,
	Constraint ERS_REIMBURSEMENTS_PK primary key (R_ID) 
);
create table ERS_REIMBURSEMENT_STATUS(
	RS_ID number(*,0) not null,
	RS_STATUS varchar2(30) not null,
	Constraint ERS_REIMBURSEMENT_STATUS_PK primary key (RS_ID)
);
create table ERS_REIMBURSEMENT_TYPE(
	RT_ID number(*,0) not null,
	RT_TYPE varchar2(30) not null,
	Constraint ERS_REIMBURSEMENT_TYPE_PK primary key (RT_ID)
);

/*Alter tables*/
Alter table ERS_REIMBURSEMENTS 
	add constraint ERS_REIMBUR_U_ID_AUTHOR_FK 
	foreign key (U_ID_AUTHOR)
	references  ERS_USERS (U_ID) on delete cascade;
	
Alter table ERS_REIMBURSEMENTS
	add constraint ERS_REIMBUR_U_ID_RESOLVER_FK 
	foreign key (U_ID_RESOLVER)
	references ERS_USERS (U_ID) on delete cascade;
	
Alter table ERS_REIMBURSEMENTS
	add constraint ERS_REIMBUR_RT_TYPE_FK
	foreign key (RT_TYPE)
	references ERS_REIMBURSEMENT_TYPE (RT_ID) on delete cascade;
	
Alter table ERS_REIMBURSEMENTS
	add constraint ERS_REIMBUR_RT_STATUS_FK
	foreign key (RT_STATUS)
	references ERS_REIMBURSEMENT_STATUS (RS_ID) on delete cascade;

Alter table ERS_REIMBURSEMENTS
modify R_SUBMITTED default current_timestamp;
	
Alter table ERS_REIMBURSEMENTS
modify RT_STATUS default 1;
/*Create sequences*/
create sequence SQ_USER_ID_ERS_USERS
start with 1
increment by 1;
/
create sequence SQ_R_ID_ERS_REIMBUR
start with 1
increment by 1;
/
create sequence SQ_UR_ID_USER_ROLES
start with 1
increment by 1;
/
create sequence SQ_RS_ID_REIMBUR_STATUS
start with 1
increment by 1;
/
create sequence SQ_RT_ID_REIMBUR_TYPE
start with 1
increment by 1;
/
create or replace trigger TR_INSERT_USER
before insert on ERS_USERS
for each row
begin
	select SQ_USER_ID_ERS_USERS.NEXTVAL into :NEW.U_ID from DUAL;
end;
/
create or replace trigger TR_INSERT_REIMBUR
before insert on ERS_REIMBURSEMENTS
for each row
begin
	select SQ_R_ID_ERS_REIMBUR.NEXTVAL into :NEW.R_ID from DUAL;
end;
/
create or replace trigger TR_INSERT_USER_ROLES
before insert on ERS_USER_ROLES
for each row
begin
	select SQ_UR_ID_USER_ROLES.NEXTVAL into :NEW.UR_ID from DUAL;
end;
/
create or replace trigger TR_INSERT_REIMBUR_STATUS
before insert on ERS_REIMBURSEMENT_STATUS
for each row
begin
	select SQ_RS_ID_REIMBUR_STATUS.NEXTVAL into :NEW.RS_ID from DUAL;
end;
/
create or replace trigger TR_INSERT_REIMBUR_TYPE
before insert on ERS_REIMBURSEMENT_TYPE
for each row
begin
	select SQ_RT_ID_REIMBUR_TYPE.NEXTVAL into :NEW.RT_ID from DUAL;
end;
/
create or replace procedure P_ADD_NEW_USER(uid out number, uname in varchar2, 
upass in varchar2, fname in varchar2, lname in varchar2, email in varchar2) as
begin
	insert into ERS_USERS (U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL)
	values(uname, upass, fname, lname, email);
	select U_ID
	into uid
	from ERS_USERS where U_USERNAME = uname and U_PASSWORD = upass;
	commit;
end;
/
create or replace procedure P_CHECK_USER_PASSWORD(uname out varchar2, upass out varchar2, 
uname2 in varchar2, upass2 in varchar2) as
begin
	select U_USERNAME, U_PASSWORD
	into uname, upass
	from ERS_USERS where U_USERNAME = uname2 and U_PASSWORD = upass2;
end;
/
﻿create or replace procedure P_GET_USER_INFO_FROM_PASSWORD(uname in varchar2, upass out varchar2, 
uids out number, fname out varchar2, lname out varchar2, email out varchar2, urid out varchar2)
as
begin
	select U_ID, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ID, U_PASSWORD
	into uids, fname, lname, email, urid, upass
	from ERS_USERS where U_USERNAME = uname;
end;
/
create or replace procedure P_ADD_NEW_REIMBUR(amt in number, descrip in varchar2, receipt in blob, 
authoid in number, ty in number) as
begin
	insert into ERS_REIMBURSEMENTS (R_AMOUNT, R_DESCRIPTION, R_RECEIPT, U_ID_AUTHOR, RT_TYPE)
	values(amt, descrip, receipt, authoid, ty);
	commit;
end;
/





