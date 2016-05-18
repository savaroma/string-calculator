INSERT INTO user(user_id, username, email, pw) VALUES (1, 'user001','user001@email.com','$2a$10$IHdRfnhNgQesPFD5hrUcMOvyx5RrRcklkpXfs9YX4j1qXvouEeVIa');
INSERT INTO user(user_id, username, email, pw) VALUES (2, 'user002','user002@email.com','$2a$10$NlU0bdBUiegZWZvl6CGpj.wV5YfbDGZ8lYznxWp2NNE4F9cYJJFOe');
INSERT INTO user(user_id, username, email, pw) VALUES (3, 'user003','user003@email.com','$2a$10$gwEVdI6lSDrkIkGLrsHTIOzLEgnT3gDUDYMOfxvOdnoqvGpf275fm');
INSERT INTO user(user_id, username, email, pw) VALUES (4, 'user004','user004@email.com','$2a$10$W2ZJXI00xhL03vwcy2Y/DeZe.BqMf4dUSP5lxEQFAqV.ocbUAYS4m');
INSERT INTO user(user_id, username, email, pw) VALUES (5, 'user005','user005@email.com','$2a$10$Q209gpOY73eZM5/7ix8Hxua/d8cPiV0nhBF.cPgEmtoY.2WN3z/k6');
INSERT INTO user(user_id, username, email, pw) VALUES (6, 'user006','user006@email.com','$2a$10$6njApozqiAlwamwi8oqgF.70eeTpgl4Z4SUpKK72AnIhHd3WXK/ei');
INSERT INTO user(user_id, username, email, pw) VALUES (7, 'user007','user007@email.com','$2a$10$YQifsq3fEABCJNM.ebxlmuJTNSvtJR72jZWHxLoU8A6Lap1QV/WP6');
INSERT INTO user(user_id, username, email, pw) VALUES (8, 'user008','user008@email.com','$2a$10$o0AykWkpgbKak8EyMM/J2.ntdjieJnf6.vgtcQVwnSW6n74YkjQMu');
INSERT INTO user(user_id, username, email, pw) VALUES (9, 'user009','user009@email.com','$2a$10$rbDB/WLckQTAO4St9TpMVOzSlJDrx98r2jgt9crwtsy8hCv7wIZkm');
INSERT INTO user(user_id, username, email, pw) VALUES (10, 'savaroma','savaroma@email.com','$2a$10$IHdRfnhNgQesPFD5hrUcMOvyx5RrRcklkpXfs9YX4j1qXvouEeVIa');

INSERT INTO follower(follower_id, followee_id) VALUES (1, 2);
INSERT INTO follower(follower_id, followee_id) VALUES (1, 7);
INSERT INTO follower(follower_id, followee_id) VALUES (1, 10);
INSERT INTO follower(follower_id, followee_id) VALUES (1, 9);

INSERT INTO message(author_id, text, result, pub_date) VALUES (10,'2+2','4', TIMESTAMP '2014-01-15 02:00:22');
INSERT INTO message(author_id, text, result, pub_date) VALUES (10,'123.12 + 34 * (12 - 10)','191.12', TIMESTAMP '2014-01-15 02:00:22');
INSERT INTO message(author_id, text, result, pub_date) VALUES (10,'-123.12 + 34','-89.12', TIMESTAMP '2014-01-15 02:00:22');
