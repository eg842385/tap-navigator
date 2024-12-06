BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('brewer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');

INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Hoof Hearted', '3', 'Get it?','300 County Rd 26', 'Merengo', 'Ohio', '43334');
INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Urban Artifact', '3', 'Reward the Curious.','1660 Blue Rock St', 'Cincinnati', 'Ohio', '45223');
INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Great Lakes Brewing Company', '3', 'Much abrew about something.','2516 Market Ave', 'Cleveland', 'Ohio', '44113');

INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('3', 'Lake Erie Monster', 'Imperial Double IPA','gonna get you DRUNK', '9.5', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('3', 'Nosferatu', 'Imperial Red IPA','more alcohol than blood', '8.5', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('3', 'Vanilla Blackout Stout', 'Imperial Stout','the name says it all', '10.5', 'a url');




COMMIT TRANSACTION;
