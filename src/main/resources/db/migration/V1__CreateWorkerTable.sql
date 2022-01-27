create table if not exists worker
(
    id     int not null,
    name   varchar(255),
    shifts json,
    primary key (id)
);