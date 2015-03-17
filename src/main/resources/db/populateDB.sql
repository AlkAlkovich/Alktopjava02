DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');
-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 100000);
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_ADMIN', 100001);

INSERT INTO meals (description,datetime,calories,user_by_id)
  VALUES ('Fastfood','2015-01-01',1999,100000);
INSERT INTO meals (description,datetime,calories,user_by_id)
  VALUES ('chiken','2015-01-05',1939,100000);
INSERT INTO meals (description,datetime,calories,user_by_id)
  VALUES ('will be between','2015-01-03',19239,100000);


