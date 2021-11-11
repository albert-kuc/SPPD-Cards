DROP TABLE IF EXISTS `card` CASCADE;

CREATE TABLE `card`(
    id INTEGER AUTO_INCREMENT,
    name VARCHAR(255),
    theme VARCHAR(255),
    class_type VARCHAR(255),
    rarity VARCHAR(255),
    cost INTEGER,
    PRIMARY KEY (id)
);
