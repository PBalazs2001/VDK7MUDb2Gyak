create or replace function PilotCount return int as f_result int;
begin
select count(idnumber) into f_result from Pilot;
return(f_result);
end;