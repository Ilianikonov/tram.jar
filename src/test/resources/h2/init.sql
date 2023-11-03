create table tram_route
(
    id       BIGINT auto_increment primary key,
    name     varchar(500) not null,
    code     varchar(50) NOT NULL unique
);