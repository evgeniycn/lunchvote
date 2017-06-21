DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM restraunts;
DELETE FROM VOTES;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (name, email, password, LAST_VOTE_DATE) VALUES
  ('User1', 'user1@user.com', '$2a$10$/DexUoa4UClouvESi6j7OuYf9n8XctfKJivJR0C9wmI7F4KXMOgQK', NULL),
  ('User2', 'user2@user.com', '$2a$10$TS7ZEXX.LV2w/aEfuFs0jeb9Jd0oUFQ4Ju8WrI3R/zemOLypen4q6', now),
  ('User3', 'user3@user.com', '$2a$10$1NAFmCIIwAT0mQXL7.2d4.dwBNLeuv2FQQCVbp8EAdLAEsv3q3rdK', now),
  ('Admin1', 'admin1@gmail.com', '$2a$10$gZKnUn..TzzYJYAF5loXOufg0ZARg1EMzRnjuWs34uSZghjLwBNsO', NULL),
  ('Admin2', 'admin2@gmail.com', '$2a$10$dcakbnp1euOfDmtvAyXK9.kQQZfaOnEr7NcvQNRU3bqOUYZhqlqIa', NULL);

INSERT INTO USER_ROLES (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100003),
  ('ROLE_ADMIN', 100004),
  ('ROLE_USER', 100004);

INSERT INTO DISHES (name, date, price, restraunt_id) VALUES
  ('Borsh',now, 100.00, 100011),
  ('Salat', '2015-05-30', 50.00, 100011),
  ('Macaroni','2015-05-30', 80.00, 100011),
  ('Soup', '2015-05-31', 50.00, 100012),
  ('Sandwich','2015-05-31', 55.50, 100012),
  ('Jarkoe', '2015-05-31', 100.00, 100013);

INSERT INTO RESTRAUNTS (name, update_date) VALUES
  ('Restraunt1', now),
  ('Restraunt2', now),
  ('Restraunt3', '2015-05-30'),
  ('Restraunt4', '2015-05-31'),
  ('Restraunt5', '2015-05-31'),
  ('Restraunt6', '2015-05-31');

INSERT INTO VOTES (restraunt_id, user_id, date) VALUES
  (100011, 100000, '2015-05-31'),
  (100011, 100001, now),
  (100011, 100002, '2015-05-31'),
  (100012, 100003, '2015-05-31'),
  (100012, 100004, '2015-05-31'),
  (100014, 100000, '2015-05-30'),
  (100014, 100004, '2015-05-30'),
  (100011, 100002, '2015-05-29'),
  (100011, 100002, now);