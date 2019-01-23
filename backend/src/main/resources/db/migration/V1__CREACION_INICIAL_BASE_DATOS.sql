/*==============================================================*/
/* Table: comuna                                                */
/*==============================================================*/
create table comuna (
   id_comuna            int4                 not null,
   id_provincia         int4                 null,
   nombre               varchar(100)         not null,
   constraint pk_comuna primary key (id_comuna)
);

/*==============================================================*/
/* Table: perfil_usuario                                        */
/*==============================================================*/
create table perfil_usuario (
   id_perfil_usuario    int4                 not null,
   nombre               varchar(100)         not null,
   constraint pk_perfil_usuario primary key (id_perfil_usuario)
);

/*==============================================================*/
/* Table: persona                                               */
/*==============================================================*/
create table persona (
   id_persona           bigserial            not null,
   id_comuna            int4                 null,
   run                  int4                 null,
   nombres              varchar(255)         not null,
   apellido_paterno      varchar(255)         not null,
   apellido_materno      varchar(255)         null,
   fechanacimiento      date                 null,
   telefono             varchar(20)          null,
   email                varchar(100)         not null,
   constraint pk_persona primary key (id_persona)
);

/*==============================================================*/
/* Index: idx_persona_email                                     */
/*==============================================================*/
create unique index idx_persona_email on persona (
email
);

/*==============================================================*/
/* Index: idx_persona_run                                       */
/*==============================================================*/
create unique index idx_persona_run on persona (
run
);


/*==============================================================*/
/* Table: provincia                                             */
/*==============================================================*/
create table provincia (
   id_provincia         int4                 not null,
   id_region            int4                 null,
   nombre               varchar(100)         not null,
   constraint pk_provincia primary key (id_provincia)
);

/*==============================================================*/
/* Table: region                                                */
/*==============================================================*/
create table region (
   id_region            int4                 not null,
   nombre               varchar(100)         not null,
   constraint pk_region primary key (id_region)
);

/*==============================================================*/
/* Table: usuario                                               */
/*==============================================================*/
create table usuario (
   id_persona           int8                 not null,
   id_perfil_usuario    int4                 not null,
   username             varchar(255)         not null,
   password             varchar(500)         null,
   habilitado           boolean              not null,
   constraint pk_usuario primary key (id_persona)
);

/*==============================================================*/
/* Index: idx_usuario_username                                  */
/*==============================================================*/
create unique index idx_usuario_username on usuario (
username
);

alter table comuna
   add constraint fk_comuna_reference_provinci foreign key (id_provincia)
      references provincia (id_provincia)
      on delete restrict on update restrict;

alter table provincia
   add constraint fk_provinci_reference_region foreign key (id_region)
      references region (id_region)
      on delete restrict on update restrict;

alter table usuario
   add constraint fk_usuario_reference_persona foreign key (id_persona)
      references persona (id_persona)
      on delete restrict on update restrict;

alter table usuario
   add constraint fk_usuario_reference_perfil_u foreign key (id_perfil_usuario)
      references perfil_usuario (id_perfil_usuario)
      on delete restrict on update restrict;

alter table persona
   add constraint fk_persona_reference_comuna foreign key (id_comuna)
      references comuna (id_comuna)
      on delete restrict on update restrict;
