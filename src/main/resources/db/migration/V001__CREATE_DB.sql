create database travel;
create user 'travel'@'localhost' identified by 'mvLJUW9p32ad3FiH';
create user 'travel'@'%' identified by 'mvLJUW9p32ad3FiH';
grant all on travel.* to 'travel'@'localhost';
grant all on travel.* to 'travel'@'%';
