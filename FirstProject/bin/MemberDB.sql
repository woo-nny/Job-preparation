CREATE TABLE login(
id_number varchar2(100) primary key,
id varchar2(100) not null,
pw varchar2(100) not null,
name varchar2(100),
job varchar2(100),
test_num number(10)
)

create table test(
id_number varchar2(100),
day_date varchar2(100),
t_score varchar2(100),
n_score varchar2(100),
k_score varchar2(100),
constraint login_id_number foreign key(id_number) 
references login(id_number)
)

create sequence sc_num start with 1 increment by 1


drop sequence sc_num
DROP TABLE LOGIN
drop table test

SELECT * FROM login;
select * from test;

