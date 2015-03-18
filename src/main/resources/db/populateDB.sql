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
  VALUES ('meal1','2015-01-01',10,100000);
INSERT INTO meals (description,datetime,calories,user_by_id)
  VALUES ('meal2','2015-01-02',11,100000);
INSERT INTO meals (description,datetime,calories,user_by_id)
  VALUES ('meal3','2015-01-03',12,100000);
INSERT INTO meals (description,datetime,calories,user_by_id)
  VALUES ('meal4','2015-01-04',13,100000);


