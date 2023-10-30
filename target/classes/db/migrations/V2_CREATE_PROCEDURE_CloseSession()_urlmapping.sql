CREATE PROCEDURE CloseSession()

DELIMITER = //
CREATE PROCEDURE CreateLink(IN url_long VARCHAR(999), OUT url_short VARCHAR(25))
BEGIN
	DECLARE new_id INT;
    SET new_id = (SELECT MAX(id) FROM links) +1;
    SET url_short = CONCAT('http://localhost:3306/', new_id);
    INSERT INTO links(short_url, long_url) VALUES(url_short,url_long);
    END // DELIMITER;

    DELIMITER //
CREATE PROCEDURE GetLink(IN url_short VARCHAR(25), OUT url_long VARCHAR(999))
BEGIN
	SELECT long_url INTO url_long FROM links WHERE short_url = url_short;
    CALL CloseSession();
    END // DELIMITER;

    DELIMITER //
CREATE PROCEDURE CalculateTime(IN creation_time DATETIME, OUT creation_time DATETIME)
BEGIN
	DECLARE current_time TIMESTAMP;
    SET current_time = NOW();

    SELECT TIMESTAMPDIFF (SECOND, session_start_time, current_time) INTO @session_duration;
    SELECT @session_duration AS 'Tempo de criação';
    END;

