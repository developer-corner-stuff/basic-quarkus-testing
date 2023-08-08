-- Create initial books to test with
INSERT INTO book(id, title, author) VALUES (nextval('book_sequence'), 'Foundation', 'Isaac Asimov');
INSERT INTO book(id, title, author) VALUES (nextval('book_sequence'), 'Dune', 'Frank Herbert');
INSERT INTO book(id, title, author) VALUES (nextval('book_sequence'), 'The Three Body Problem', 'Cixin Liu');
INSERT INTO book(id, title, author) VALUES (nextval('book_sequence'), 'The Shape of Things to Come', 'H.G. Wells');

-- Create initial book sellers to test with
INSERT INTO bookseller(id, name, description) VALUES (nextval('bookseller_sequence'), 'Ad Astra', 'Sci Fi Book Seller');
INSERT INTO bookseller(id, name, description) VALUES (nextval('bookseller_sequence'), 'The Jumping Frog', 'Classic Book Seller but mostly Mark Twain');
INSERT INTO bookseller(id, name, description) VALUES (nextval('bookseller_sequence'), 'Sundog Books', 'Live a great story');
INSERT INTO bookseller(id, name, description) VALUES (nextval('bookseller_sequence'), 'Skylight Books', 'Art books by the pound');
