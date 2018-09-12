create table product(
  id int not null primary key,
  name varchar(255) not null unique,
  price decimal(10,2) not null
);

create sequence product_id_seq;

create domain order_num as int
   check(value >= 0);

create sequence purchase_id_seq;

create table purchase(
  id int not null primary key,
  num order_num not null,
  product_id int not null references product(id) on delete cascade,
  count int not null
);

create index on purchase (num);