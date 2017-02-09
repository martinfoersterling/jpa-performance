
    drop table Familie if exists;

    drop table Marke if exists;

    drop table Modellgruppe if exists;

    drop sequence if exists JPA_ID_SEQUENCE;

    create table Familie (
        ID bigint not null,
        OPT_LOCK bigint not null,
        beschreibung clob,
        name varchar(255),
        MODELLGRUPPE_ID bigint not null,
        primary key (ID)
    );

    create table Marke (
        ID bigint not null,
        OPT_LOCK bigint not null,
        name varchar(255),
        primary key (ID)
    );

    create table Modellgruppe (
        ID bigint not null,
        OPT_LOCK bigint not null,
        name varchar(255),
        MARKE_ID bigint not null,
        primary key (ID)
    );

    alter table Familie 
        add constraint FK_FAMILIE_MODELLGRUPPE 
        foreign key (MODELLGRUPPE_ID) 
        references Modellgruppe;

    alter table Modellgruppe 
        add constraint FK_MODELLGRUPPE_MARKE 
        foreign key (MARKE_ID) 
        references Marke;

    create sequence JPA_ID_SEQUENCE start with 1 increment by 50;
