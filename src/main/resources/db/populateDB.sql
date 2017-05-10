DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM restraunts;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS (name, email, password)
VALUES ('User1', 'user1@user.com', 'password');

INSERT INTO USERS (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO USER_ROLES (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO DISHES (name, date, price, restraunt_id) VALUES
  ('Borsh','2015-05-30', 100.00, 100008),
  ('Salat', '2015-05-30', 50.00, 100008),
  ('Macaroni','2015-05-30', 80.00, 100008),
  ('Soup', '2015-05-31', 50.00, 100009),
  ('Sandwich','2015-05-31', 55.50, 100009),
  ('Jarkoe', '2015-05-31', 100.00, 100010);

INSERT INTO RESTRAUNTS (name, update_date) VALUES
  ('Restraunt1', '2015-05-30'),
  ('Restraunt2', '2015-05-31'),
  ('Restraunt3', '2015-05-30'),
  ('Restraunt4', '2015-05-31'),
  ('Restraunt5', '2015-05-31'),
  ('Restraunt6', '2015-05-31');