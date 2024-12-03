BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Hoof Hearted', '1', 'Get it?','300 County Rd 26', 'Merengo', 'Ohio', '43334');
INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Urban Artifact', '1', 'Reward the Curious.','1660 Blue Rock St', 'Cincinnati', 'Ohio', '45223');
INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Great Lakes Brewing Company', '1', 'Much abrew about something.','2516 Market Ave', 'Cleveland', 'Ohio', '44113');

COMMIT TRANSACTION;
