CREATE DEFINER=`root`@`localhost` PROCEDURE `ReturnLink`(IN url_short VARCHAR(25), OUT url_long VARCHAR(999))
BEGIN
	SELECT long_url INTO url_long FROM links WHERE short_url = url_short;
    CALL CloseSession();
    END