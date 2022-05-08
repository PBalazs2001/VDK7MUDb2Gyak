create or replace trigger PilotTrigger
before insert or delete or update on Pilot for each row
begin
    if inserting then
        insert into DataLog values(current_timestamp, 'insert', 'Pilot', user);
    end if;
    if deleting then
        insert into DataLog values(current_timestamp, 'delete', 'Pilot', user);
    end if;
    if updating then
        insert into DataLog values(current_timestamp, 'modify', 'Pilot', user);
    end if;
end;