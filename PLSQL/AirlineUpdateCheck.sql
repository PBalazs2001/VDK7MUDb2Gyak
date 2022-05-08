create or replace trigger AirlineUpdateCheck before update on Airline for each row
begin
    if not ((:new.payment / (:old.payment / 100)) >= 80 and (:new.payment / (:old.payment / 100)) <= 120) then
        if :old.payment < :new.payment then
            :new.payment := :old.payment * 1.2;
        end if;
        if :new.payment < :old.payment then
            :new.payment := :old.payment * 0.8;
        end if;
        dbms_output.put_line('More than 20%');
    end if;
end;