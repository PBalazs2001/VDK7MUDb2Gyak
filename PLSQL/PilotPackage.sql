create or replace package PilotPackage is
    procedure NewPilot(p_id number, p_sex char, p_age number);
    procedure UpdatePilot(p_id number, p_sex char, p_age number);
    procedure DeletePilot(p_id number);
    function PilotCount return int;
end PilotPackage;