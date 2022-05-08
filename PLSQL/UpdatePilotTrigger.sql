create or replace trigger UpdatePilot before update on Pilot for each row
begin
    insert into DataLog values(current_timestamp, 'modify', 'Pilot', user);
end;