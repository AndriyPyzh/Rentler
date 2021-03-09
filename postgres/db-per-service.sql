create or replace procedure create_user_for_db(db varchar)
    language plpgsql
as
$$
declare
    usr   varchar;
    pswrd varchar;
begin
    usr := db || '_user';
    pswrd := db || '_password';

    execute (format('create user %s with password ''%s'';', usr, pswrd) ||
             format('grant all privileges on database %s to %s;', db, usr)) ||
             format('grant all privileges on database %s to %s;', db, 'postgres');
end ;
$$;


create database user_service;
call create_user_for_db('user_service');

create database apartment_service;
call create_user_for_db('apartment_service');



