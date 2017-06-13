DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM restraunts;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (name, email, password) VALUES
  ('User1', 'user1@user.com', 'password1'),
  ('User2', 'user2@user.com', 'password2'),
  ('User3', 'user3@user.com', 'password3'),
  ('Admin1', 'admin1@gmail.com', 'admin1'),
  ('Admin2', 'admin2@gmail.com', 'admin2');

INSERT INTO USER_ROLES (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO DISHES (name, date, price, restraunt_id) VALUES
  ('Borsh','2015-05-30', 100.00, 100011),
  ('Salat', '2015-05-30', 50.00, 100011),
  ('Macaroni','2015-05-30', 80.00, 100011),
  ('Soup', '2015-05-31', 50.00, 100012),
  ('Sandwich','2015-05-31', 55.50, 100012),
  ('Jarkoe', '2015-05-31', 100.00, 100013);

INSERT INTO RESTRAUNTS (name, update_date, votes) VALUES
  ('Restraunt1', '2015-05-30', 0),
  ('Restraunt2', '2015-05-31', 5),
  ('Restraunt3', '2015-05-30', 2),
  ('Restraunt4', '2015-05-31', 1),
  ('Restraunt5', '2015-05-31', 7),
  ('Restraunt6', '2015-05-31', 0);

INSERT INTO VOTES (restraunt_id, user_id, date) VALUES
  (100011, 100000, '2015-05-31'),
  (100011, 100001, '2015-05-31'),
  (100011, 100002, '2015-05-31'),
  (100012, 100003, '2015-05-31'),
  (100012, 100004, '2015-05-31'),
  (100014, 100000, '2015-05-30'),
  (100014, 100004, '2015-05-30'),
  (100011, 100002, '2015-05-29'),
  (100011, 100004, '2015-05-29');