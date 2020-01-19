create table securityuser(
 id bigint(20) not null auto_increment,
 username varchar(50) not null,
 password varchar(60),
 enable tinyint(4) not null default 1,
 roles text character set utf8 comment 'user roles',
 primary key (id),
 key username (username)
);

create table persistent_logins(
username varchar(64) not null,
series varchar(64) primary key,
token varchar(64) not null,
last_used timestamp not null
)

insert into securityuser(username, password, roles) values("admin", "123", "ROLE_ADMIN,ROLE_USER");
insert into securityuser(username, password, roles) values("user", "123", "ROLE_USER");