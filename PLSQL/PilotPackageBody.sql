create or replace package body PilotPackage is
procedure NewPilot(p_id number, p_sex char, p_age number) is
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

procedure UpdatePilot(p_id number, p_sex char, p_age number) is
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

procedure DeletePilot(p_id number) is
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

function PilotCount return int as f_result int;
begin
select count(idnumber) into f_result from Pilot;
return(f_result);
end;

end PilotPackage;