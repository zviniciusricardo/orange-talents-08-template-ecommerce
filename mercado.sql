drop table if exists categoria CASCADE;

drop table if exists perfil CASCADE;

drop table if exists usuario CASCADE;

drop table if exists usuario_perfis CASCADE;

show tables;

create table categoria (
	id bigint not null,
	nome varchar(255),
    categoria_mae_id bigint,
    primary key (id)
);

create table perfil (
   id bigint not null,
    nome varchar(255),
    primary key (id)
);

create table usuario (
   id bigint not null,
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
     "08/09/2021 21:05:0003",
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
     "08/09/2021 21:05:0004",
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
     "08/09/2021 21:05:0005",
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
     "08/09/2021 21:05:0006",
     "email4@email.com",
     "$2a$10$EU1IB1MWCPM8m2RUkRm5wOFVe7owSNT0GdBqTeOiw61/gMQSH5kY6"
     );


insert into perfil (id, nome) values (1, "ROLE_ADMIN");
insert into perfil (id, nome) values (2, "ROLE_CLIENT");

insert into usuario_perfis (usuario_id, perfis_id) values (1, 1);
insert into usuario_perfis (usuario_id, perfis_id) values (2, 2);
insert into usuario_perfis (usuario_id, perfis_id) values (3, 2);
insert into usuario_perfis (usuario_id, perfis_id) values (4, 2);