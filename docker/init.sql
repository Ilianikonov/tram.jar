drop table if exists tram_route;

create table tram_route
(
    id       BIGINT auto_increment primary key,
    name     varchar(500) not null,
    code     varchar(50) NOT NULL unique
);

create index tram_route_id_index on tram_route(id);
create index tram_route_name_index on tram_route(name);