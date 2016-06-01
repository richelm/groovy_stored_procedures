/*
 * employee table
 */

use testdb
go

-- drop database object if already exist
if object_id('employee') is not null
  drop table employee
go

-- create the table
create table employee (
  employee_id     int,
  first_name      varchar(40),
  last_name       varchar(40),
  email           varchar(60),
  start_date      date,
  sick_days       int,
  salary          money
)
go

-- load with inital values for testing
insert into employee values(101,"Buggs","Bunny","bbunny@nowhere","7/19/1995",27,$15345.0)
insert into employee values(102,"Daffy","Duck","duck@nowhere","12/15/2001",35,$17453.0)
insert into employee values(103,"Elmer","Fudd","fudd@nowhere","8/28/1988",50,$21540.0)
insert into employee values(104,"Yosemite","Sam","samy@nowhere","3/15/2003",11,$10560.0)
insert into employee values(105,"Foghorn","Leghorn","rooster@nowhere","3/15/2003",23,$17560.0)
go
