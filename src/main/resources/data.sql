use firstproject_db;
INSERT INTO article(title, content) values ('가가가가가', '11111');
INSERT INTO article(title, content) values ('나나나나나', '22222');
INSERT INTO article(title, content) values ('다다다다다', '33333');

INSERT INTO article(title, content) values ('당신의 인생 영화는?', '댓글 고');
INSERT INTO article(title, content) values ('당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article(title, content) values ('당신의 취미는?', '댓글 고고고');

INSERT INTO comment(article_id, nickname, body) VALUES (4, 'park', '굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'kim', '아이 엠 샘');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'choi', '쇼생크 탈출');

INSERT INTO comment(article_id, nickname, body) VALUES (5, 'park', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'kim', '샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'choi', '초밥');

INSERT INTO comment(article_id, nickname, body) VALUES (6, 'park', '조깅');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'kim', '유튜브 시청');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'choi', '독서');

INSERT INTO member(id, email, password) values (1, 'mail@cloudstudying.kr', '1111');
INSERT INTO member(id, email, password) values (2, 'yangingyu@naver.com', '2222');
INSERT INTO member(id, email, password) values (3, 'ingyu@gmail.com', '3333');

INSERT INTO coffee(name, price) values ('아메리카노', '4500');
INSERT INTO coffee(name, price) values ('라떼', '5000');
INSERT INTO coffee(name, price) values ('카페 모카', '5500');


