/* CREATE SEQUENCE Pilot_seq; */
CREATE OR REPLACE TRIGGER Pilot_insert
  BEFORE INSERT ON Pilot
  FOR EACH ROW
BEGIN
  SELECT Pilot_seq.nextval
  INTO :new.idnumber
  FROM dual;
END;