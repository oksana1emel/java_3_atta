ALTER TABLE item
ADD CONSTRAINT item_constraint CHECK (resourceid > 0 AND count >= 0 AND level >= 0);

ALTER TABLE player
ADD CONSTRAINT player_unique UNIQUE (nickname);

ALTER TABLE currency
ADD CONSTRAINT currency_constraint CHECK (count >= 0 AND resourceid > 0);

ALTER TABLE progress
DROP CONSTRAINT progress_constraint;

ALTER TABLE progress
ADD CONSTRAINT progress_constraint 
CHECK (maxscore >= 0 AND resourceid > 0 AND score >= 0);