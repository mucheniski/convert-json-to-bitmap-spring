show databases;

create database bvtests;

use bvtests;

grant all privileges on bvtests.* to 'developer'@'localhost';

flush privileges;

