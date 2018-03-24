DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, dateTime, description, calories) VALUES
  (100000, '2018-03-22 10:00:00', 'завтрак', 500),
  (100000, '2018-03-22 13:00:00', 'обед', 1000),
  (100000, '2018-03-22 20:00:00', 'ужин', 500),
  (100000, '2018-03-23 10:00:00', 'завтрак', 500),
  (100000, '2018-03-23 13:00:00', 'обед', 1000),
  (100000, '2018-03-23 20:00:00', 'ужин', 510),
  (100001, '2018-03-22 10:00:00', 'завтрак', 500),
  (100001, '2018-03-22 13:00:00', 'обед', 500),
  (100001, '2018-03-22 20:00:00', 'ужин', 700),
  (100001, '2018-03-23 10:00:00', 'завтрак', 500),
  (100001, '2018-03-23 13:00:00', 'обед', 1000),
  (100001, '2018-03-23 20:00:00', 'ужин', 700);

