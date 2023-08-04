-- create sequence book_id_seq start 1 increment 1;
--
-- create table book (
--     id long not null,
--     title varchar(255),
--     author varchar(255),
--     primary key (id)
-- )

INSERT INTO book(id, title, author) VALUES (nextval('book_sequence'), 'Foundation', 'Isaac Asimov');
INSERT INTO book(id, title, author) VALUES (nextval('book_sequence'), 'Dune', 'Frank Herbert');
INSERT INTO book(id, title, author) VALUES (nextval('book_sequence'), 'The Three Body Problem', 'Cixin Liu');
INSERT INTO book(id, title, author) VALUES (nextval('book_sequence'), 'The Shape of Things to Come', 'H.G. Wells');
--ALTER SEQUENCE  book_sequence RESTART WITH 5;