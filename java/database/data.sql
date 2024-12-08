BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('brewer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('mike','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('tina','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('erika','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('stephanie','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('syd','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_BREWER');




INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Hoof Hearted', '4', 'Get it?','300 County Rd 26', 'Merengo', 'Ohio', '43334');
INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Urban Artifact', '8', 'Reward the Curious.','1660 Blue Rock St', 'Cincinnati', 'Ohio', '45223');
INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Great Lakes Brewing Company', '6', 'Much abrew about something.','2516 Market Ave', 'Cleveland', 'Ohio', '44113');
INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Fat Heads Brewery', '7', 'Chill out man, have a beer!','18741 Sheldon Rd.', 'Middleburg Heights', 'Ohio', '44130');
INSERT INTO brewery (name, user_id, description, address, city, state, zipcode) VALUES ('Hi Wire Brewing', '5', 'Chill out man, have a beer!','4600 Beech Street, Suites 100 and 300', 'Norwood', 'Ohio', '45212');



INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('3', 'Lake Erie Monster', 'Imperial Double IPA','gonna get you DRUNK', '9.5', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('3', 'Nosferatu', 'Imperial Red IPA','more alcohol than blood', '8.5', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('3', 'Vanilla Blackout Stout', 'Imperial Stout','the name says it all', '10.5', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('2', 'Gadget', 'Midwest Fruit Tart','Stands as an award-winning masterpiece. Crafted with thousands of pounds of blackberries and raspberries, along with a touch of vanilla, it’s celebrated far and wide for its exceptional flavor.', '8.0', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('2', 'Peaches & Cream', 'Nitro Fruit Tart','Whether you’re a fruit tart aficionado or new to nitrogen-infused brews, this is a taste journey you won’t want to miss. Open the can, pour, and let the indulgence begin!', '7.1', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('2', 'Archaeopteryx', 'Fruit Tart','A blend of wild-caught cultures, curated to highlight the terrior of the Ohio River Valley and aged on whole Obsidian Berries.', '8.9', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('1', 'Combat Spandex', 'Sour Triple IPA','Brewed With Mango, Kiwi, Vanilla & Milk Sugar', '11.4', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('1', 'Do not Worry, My Mom Said It is cool', 'Imperial Birthday Cake Stout','Brewed with Astronaut Ice Cream, Cacao Nibs & Vanilla', '12.0', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('1', 'Girth Brooks', 'Double Mash Stout','Brewed with Hoof Hearted Coffee, Vanilla & Coconut', '15.0', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('4', 'Bumble Berry', 'Honey Blueberry Ale','Made with honey and blueberries', '5.3', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('4', 'Goggle Fogger', 'German-Style Hefe Weizen','A repeatedly award-winning wheat beer', '5.4', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('4', 'Conezilla', 'India Pale Ale','Destroyer of Dank', '6.5', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('5', 'Mountain Water', 'Easy Drinking Ale','This is a beer for drinking. A beer for hiking, hanging out, and playing outside. With big lemon-lime slushy in the sunshine energy, this is the beer to share with your seltzer friends.', '4.5', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('5', 'Hazy Pitch', 'Hazy IPA','This addition to the Pitch Family is a big Hazy IPA vibrantly bursting with juicy American hops. Waves of pineapple, melon, and citrus cascade across the palate leaving trails of tropical punch and subtle pine in the wake of this swirling hazy escape.', '7.2', 'a url');
INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES ('5', 'Hi Pitch', 'Mosaic IPA','A balanced Western North Carolina IPA with bright citrus and tropical fruit aromas. Expect big grapefruit, tangerine and subtle melon flavors from the chorus of Mosaic & Centennial hops to balance out the malt in this dank & drinkable ale.', '6.7', 'a url');


COMMIT TRANSACTION;
