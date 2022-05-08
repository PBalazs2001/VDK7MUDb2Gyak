create or replace procedure NewPilot(p_id number, p_sex char, p_age number) is
exsisterror exception;
rows_found number;
begin
    select count(*)
    into rows_found
    from Pilot
    where idnumber = p_id;
    
    if rows_found != 0 then raise exsisterror;
    else
        insert into Pilot values(p_id , p_sex , p_age);
    end if;
exception
    when exsisterror then
    dbms_output.put_line('This ID already exists');
end;