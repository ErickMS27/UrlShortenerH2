CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateLink`(IN url_long VARCHAR(999), OUT url_short VARCHAR(25))
BEGIN
DECLARE new_id INT;
    SET new_id = (SELECT MAX(id) FROM links) +1;
    SET url_short = CONCAT('http://localhost:3306/', new_id);
    INSERT INTO links(short_url, long_url) VALUES(url_short,url_long);
END