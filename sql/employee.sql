/*
 * employee table
 */

set quoted_identifier off
go

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
  start_date      datetime,
  sick_days       int,
  fringe_ratio    numeric(3,2)
)
go


-- load with inital values for testing
insert into employee values(101,"Buggs","Bunny","bbunny@nowhere","7/19/1995",27,1.5)
insert into employee values(102,"Daffy","Duck","duck@nowhere","12/15/2001",35,1.5)
insert into employee values(103,"Elmer","Fudd","fudd@nowhere","8/28/1988",50,1.5)
insert into employee values(104,"Yosemite","Sam","samy@nowhere","3/15/2003",11,1.75)
insert into employee values(105,"Foghorn","Leghorn","rooster@nowhere","3/15/2003",23,1.75)
go
