ALTER table tab_user add column role varchar(50) not null comment '角色';
ALTER TABLE tab_user;
ADD COLUMN salt VARCHAR(8);
MODIFY COLUMN password VARCHAR(128);