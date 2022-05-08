create or replace procedure UpdatePilot(p_id number, p_sex char, p_age number) is
notexsisterror exception;
rows_found number;
begin
    select count(*)
    into rows_found
    from Pilot
    where idnumber = p_id;
    
    if rows_found = 0 then raise notexsisterror;
    else
        update Pilot set idnumber = p_id, sex = p_sex, age = p_age where idnumber = p_id;
    end if;
exception
    when notexsisterror then
    dbms_output.put_line('This ID already exists');
end;