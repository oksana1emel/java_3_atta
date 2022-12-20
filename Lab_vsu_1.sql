CREATE TABLE player
(
    PlayerId SERIAL PRIMARY KEY,
    Nickname CHARACTER VARYING(30)
);

CREATE TABLE currency
(
    Id SERIAL PRIMARY KEY,
	PlayerId int REFERENCES player(PlayerId),
	ResourceId int,
    Name CHARACTER VARYING(30)
);


CREATE TABLE item
(
    Id SERIAL PRIMARY KEY,
	PlayerId int REFERENCES player(PlayerId),
	ResourceId int,
    Count int,
	Level int
);

CREATE TABLE progress
(
    Id SERIAL PRIMARY KEY,
	PlayerId int REFERENCES player(PlayerId),
	ResourceId int,
    Score int,
	MaxScore int
);

