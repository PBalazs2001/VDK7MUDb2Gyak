create or replace procedure DeletePilot(p_id number) is
notexsisterror exception;
rows_found number;
begin
    select count(*)
    into rows_found
    from Pilot
    where idnumber = p_id;
    
    if rows_found = 0 then raise notexsisterror;
    else
        delete from Pilot where idnumber = p_id;
    end if;
exception
    when notexsisterror then
    dbms_output.put_line('This ID already exists');
end;