drop table if exists categoria CASCADE

drop table if exists perfil CASCADE

drop table if exists usuario CASCADE

drop table if exists usuario_perfis CASCADE


create table categoria (
   id bigint not null auto_increment,
    nome varchar(255),
    categoria_mae_id bigint,
    primary key (id)
);

create table perfil (
   id bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
);

create table usuario (
   id bigint not null auto_increment,
    data_criacao timestamp,
    login varchar(255),
    senha varchar(255),
    primary key (id)
);

create table usuario_perfis (
   usuario_id bigint not null,
    perfis_id bigint not null
);

alter table categoria
   add constraint FKdplb73k9x71drrfia055gbot3
   foreign key (categoria_mae_id)
   references categoria;

alter table usuario_perfis
   add constraint FK7bhs80brgvo80vhme3u8m6ive
   foreign key (perfis_id)
   references perfil;

alter table usuario_perfis
   add constraint FKs91tgiyagbilt959wbufiphgc
   foreign key (usuario_id)
   references usuario;


insert into usuario (
    id,
    data_criacao,
    login,
    senha
    )
values (
     1,
     '2021-09-08 14:30:07',
     "email@email.com",
     "$2a$10$xXTTm2LnoQoN7X4ruCY6A.rPBCUn.49yd/tz3impsLOcKcpQ07IpK"
     );

insert into usuario (
    id,
    data_criacao,
    login,
    senha
    )
values (
     2,
     '2021-09-08 14:30:07',
     "email2@email.com",
     "$2a$10$3q2Z9FPHXfLO7bybAZiVduOGt8To83QWfpsgERfK1xTuA44h31C9m"
     );

insert into usuario (
    id,
    data_criacao,
    login,
    senha
    )
values (
     3,
     '2021-09-08 14:30:07',
     "email3@email.com",
     "$2a$10$Ke8eOBsRbvPyOUcgysgoK.BlSAcE0ermAZOi55BNRO/faQ60ueXPG"
     );

insert into usuario (
    id,
    data_criacao,
    login,
    senha
    )
values (
     4,
     '2021-09-08 14:30:07',
     "email4@email.com",
     "$2a$10$EU1IB1MWCPM8m2RUkRm5wOFVe7owSNT0GdBqTeOiw61/gMQSH5kY6"
     );


-- INSERE GRUPOS DE CATEGORIAS
insert into categoria ( id, nome, categoria_mae_id )
    values ( 1, 'Eletrônicos', null );


insert into categoria ( id, nome, categoria_mae_id )
    values ( 2, 'Celulares',  1 );

insert into categoria ( id, nome, categoria_mae_id )
    values ( 3, 'Televisores',  1 );

insert into categoria ( id, nome, categoria_mae_id )
    values ( 4, 'Casa', null );

insert into categoria ( id, nome, categoria_mae_id )
    values ( 5, 'Móveis', 4 );


insert into categoria ( id, nome, categoria_mae_id )
    values ( 6, 'Eletrodomésticos', 4 );


insert into categoria ( id, nome, categoria_mae_id )
    values ( 7, 'Escritório', null );


insert into categoria ( id, nome, categoria_mae_id )
    values ( 8, 'Cadeiras', 7 );

insert into categoria ( id, nome, categoria_mae_id )
    values ( 9, 'Mesas', 7 );


insert into categoria ( id, nome, categoria_mae_id )
    values ( 10, 'Video-games', 1 );




insert into perfil (id, nome) values (1, "ROLE_ADMIN");
insert into perfil (id, nome) values (2, "ROLE_CLIENT");

insert into usuario_perfis (usuario_id, perfis_id) values (1, 1);
insert into usuario_perfis (usuario_id, perfis_id) values (2, 2);
insert into usuario_perfis (usuario_id, perfis_id) values (3, 2);
insert into usuario_perfis (usuario_id, perfis_id) values (4, 2);