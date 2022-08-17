INSERT INTO languages (name)
VALUES
    ('ENGLISH'),
    ('RUSSIAN'),
    ('SPANISH'),
    ('FRENCH'),
    ('DEUTSCH'),
    ('ARABIC'),
    ('CHINESE'),
    ('JAPANESE');

INSERT INTO books (book_name, author, isbn, price, pages, binding, year_publishing, language_id)
VALUES         ('it starts us', 'Collen Hoover', '978-1668001226', 11.99, 366, 'solid', 2022, (SELECT id FROM languages WHERE name = 'ENGLISH')),
               ('I train update books', 'Collen Hoover', '978-1533723738', 11.99, 326, 'solid', 2019, (SELECT id FROM languages WHERE name = 'RUSSIAN')),
               ('Beautiful Ruins', 'Jess Walter', '978-1668001228', 13.79, 372, 'solid', 2014, (SELECT id FROM languages WHERE name = 'RUSSIAN')),
               ('Ugly love', 'Collen Hooveer', '978-1476753188', 11.86, 437, 'soft', 2004, (SELECT id FROM languages WHERE name = 'SPANISH')),
               ('Every Summer After', 'Carley Fortune', '978-0593438534', 11.40, 320, 'solid', 2020, (SELECT id FROM languages WHERE name = 'FRENCH')),
               ('The Very Hungry Caterpillar', 'Eric Carle', '978-0399226908', 5.74, 26, 'soft', 2013, (SELECT id FROM languages WHERE name = 'CHINESE')),
               ('Book Lovers', 'Emily Henry', ' 978-0593334836', 9.99, 400, 'solid', 2015, (SELECT id FROM languages WHERE name = 'CHINESE')),
               ('Big Prescool', 'Joan Hoffman', '978-0887431456', 5.99, 420, 'solid', 2022, (SELECT id FROM languages WHERE name = 'ENGLISH')),
               ('The Silent Patient', 'Alex Michaelides', '978-1250301703', 12.99, 368, 'solid', 2019, (SELECT id FROM languages WHERE name = 'ENGLISH')),
               ('The Power of Now', 'Eckhart Tolle', '978-1577314806', 11.99, 236, 'soft', 2021, (SELECT id FROM languages WHERE name = 'ENGLISH')),
               ('The Lord of the Rings Part One', 'J.R.R. Tolkien', '978-0063270886', 14.89, 432, 'solid', 2016, (SELECT id FROM languages WHERE name = 'RUSSIAN')),
               ('Fire & Blood: 300 Years Before A Game of Thrones', 'George R. R. Martin', '978-0063270835', 24.89, 754, 'solid', 2014, (SELECT id FROM languages WHERE name = 'RUSSIAN')),
               ('Maybe Someday', 'Colleen Hoover', '978-1476753164', 11.62, 384, 'solid', 2017, (SELECT id FROM languages WHERE name = 'JAPANESE')),
               ('Harry Potter Spellbook: The Unofficial ', 'Newt Flamel', '979-8447208332', 7.95, 100, 'solid', 2021, (SELECT id FROM languages WHERE name = 'ENGLISH')),
               ('Harry Potter and the Goblet of Fire ', 'J. K. Rowling', '978-0545791427', 13.99, 464, 'solid', 2021, (SELECT id FROM languages WHERE name = 'ENGLISH')),
               ('Harry Potter and the Sorcerer Stone', 'J. K. Rowling', '978-0545791422', 13.13, 345, 'solid', 2019, (SELECT id FROM languages WHERE name = 'ENGLISH')),
               ('Harry Potter and the Chamber of Secrets', 'J. K. Rowling', '978-1338716535', 7.80, 400, 'solid', 2017, (SELECT id FROM languages WHERE name = 'SPANISH')),
               ('Sparring Partners: Novellas', 'John Grisham', '978-1338715534', 16.50, 350, 'soft', 2009, (SELECT id FROM languages WHERE name = 'ENGLISH')),
               ('A Time for Mercy', 'John Grisham', '978-1338725536', 7.50, 469, 'soft', 2010, (SELECT id FROM languages WHERE name = 'ENGLISH')),
               ('Mastering the Art of French Cooking', 'Julia Child', '978-1238725736', 19.50, 1244, 'soft', 2015, (SELECT id FROM languages WHERE name = 'FRENCH') ),
               ('A Letter From Your Teacher: On the First Day of School', 'Shannon Olsen', '978-1735414126', 13.99, 31, 'soft', 2007, (SELECT id FROM languages WHERE name = 'ENGLISH'));

INSERT INTO roles (name)
VALUES
('USER'),
('MANAGER'),
('ADMIN');

INSERT INTO users (first_name, last_name, age, email, role_id)
VALUES ('Nik', 'Swanson', 35, 'n.sw@gmail.com', (SELECT id FROM roles WHERE name = 'ADMIN')),
       ('Nik', 'Meclo', 24, 'n.mcl@gmail.com', (SELECT id FROM roles WHERE name = 'ADMIN')),
       ('Sam', 'Growner', 42, 's.gr_4@mail.com', (SELECT id FROM roles WHERE name = 'USER')),
       ('Wane', 'Rooney', 39, 'WR.10@gmail.com', (SELECT id FROM roles WHERE name = 'MANAGER')),
       ('Mike', 'Ramcey', 28, 'm.rmc@gmail.com', (SELECT id FROM roles WHERE name = 'USER')),
       ('Samanta', 'Robson', 42, 'samanta_rob123@gmail.com', (SELECT id FROM roles WHERE name = 'ADMIN')),
       ('Selena', 'Gomes', 43, 'selena_love_sing@gmail.com', (SELECT id FROM roles WHERE name = 'USER')),
       ('Raley', 'Rricey', 56, 'ral.12387@mail.com', (SELECT id FROM roles WHERE name = 'MANAGER')),
       ('Brad', 'Spacey', 35, 'br_spacey.com', (SELECT id FROM roles WHERE name = 'ADMIN')),
       ('Helena', 'Rassy', 42, 'helena.ras456@gmail.com', (SELECT id FROM roles WHERE name = 'USER')),
       ('Mike', 'Walent', 50, 'mik.walent@mail.com', (SELECT id FROM roles WHERE name = 'MANAGER')),
       ('Stasey', 'Lacatelli', 35, 'locat.ital12@gmail.com', (SELECT id FROM roles WHERE name = 'ADMIN')),
       ('Jeck', 'Peterson', 46, 'pet.jeck1987@gmail.com', (SELECT id FROM roles WHERE name = 'USER')),
       ('Molly', 'Swanson', 22, 'swans.mol1999@gmail.com', (SELECT id FROM roles WHERE name = 'USER')),
       ('Rob', 'Jeferson', 17, 'jeferson19991999@gmail.com', (SELECT id FROM roles WHERE name = 'USER')),
       ('Jim', 'Paranga', 40, 'par.jim_1998@mail.com', (SELECT id FROM roles WHERE name = 'USER')),
       ('Samanta', 'Metry', 35, 'sam.petry15678@gmail.com', (SELECT id FROM roles WHERE name = 'ADMIN')),
       ('Sam', 'Cook', 19, 'cokky_sam1987@gmail.com', (SELECT id FROM roles WHERE name = 'USER')),
       ('Wolly', 'Monson', 59, 'monson_1967@gmail.com', (SELECT id FROM roles WHERE name = 'MANAGER')),
       ('Nik', 'Cracks', 49, 'cr_nik_1979@gmail.com', (SELECT id FROM roles WHERE name = 'ADMIN')),
       ('Silly', 'Sorento', 42, 'sil.srn18@gmail.com', (SELECT id FROM roles WHERE name = 'USER'));