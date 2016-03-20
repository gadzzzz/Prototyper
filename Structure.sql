--Table USERCONNECTION

CREATE TABLE UserConnection (
    userId number not null,
    providerId varchar2(255) not null,
    providerUserId varchar2(255),
    primary key (userId, providerId, providerUserId));

CREATE SEQUENCE user_id START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER user_objectid_trigger
BEFORE INSERT ON UserConnection
FOR EACH ROW
BEGIN
  :NEW.userId:=user_id.nextval;
END;
/

--Table PROTOTYPES

CREATE TABLE Prototypes(
prototypeId number primary key,
userId number not null,
updateDate date not null,
name varchar2(40) not null
);

CREATE SEQUENCE prototype_id START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER prototypes_objectid_trigger
BEFORE INSERT ON Prototypes
FOR EACH ROW
BEGIN
  :NEW.prototypeId:=prototype_id.nextval;
END;
/

--Table PAGES

CREATE TABLE pages(
pageId number primary key,
prototypeId number not null,
doc CLOB
);

CREATE SEQUENCE page_id START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER pages_objectid_trigger
BEFORE INSERT ON PAGES
FOR EACH ROW
BEGIN
  :NEW.pageId:=page_id.nextval;
END;
/