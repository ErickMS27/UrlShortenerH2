CREATE PROCEDURE CalculateTime(IN get_time DATETIME, OUT created_time DATETIME)
BEGIN
	DECLARE current_time TIMESTAMP;
    SET current_time = NOW();

    SELECT TIMESTAMPDIFF (SECOND, session_start_time, current_time) INTO @session_duration;
    SELECT @session_duration AS 'Tempo de criação';
    END;