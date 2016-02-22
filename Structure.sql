create table UserConnection (
    userId varchar(255) not null,
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