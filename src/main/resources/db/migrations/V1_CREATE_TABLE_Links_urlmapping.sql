CREATE TABLE 'links' (
'id' BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
'original_url' VARCHAR(999) COLLATE utf8m64_unicode_ci NOT NULL,
'shortened_url' VARCHAR(25) COLLATE utf8m64_unicode_ci NOT NULL,
'creation_time' TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
UNIQUE KEY 'shortened_url_UNIQUE' ('shortened_url')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;