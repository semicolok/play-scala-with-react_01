create table board (
  id bigint not null auto_increment primary key,
  title varchar(256) not null,
  content text,
  writer varchar(256) not null,
  created_at timestamp not null default current_timestamp,
  updated_at timestamp not null default current_timestamp
);
