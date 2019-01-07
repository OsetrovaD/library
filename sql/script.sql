CREATE DATABASE library_app encoding='UTF8';

CREATE SCHEMA library_app_storage;

CREATE TABLE library_app_storage.genre (
  id BIGSERIAL PRIMARY KEY,
  name CHARACTER VARYING(256) UNIQUE NOT NULL
);

CREATE TABLE library_app_storage.author (
  id BIGSERIAL PRIMARY KEY,
  name CHARACTER VARYING(256) UNIQUE NOT NULL,
  date_of_birth DATE
);

CREATE TABLE library_app_storage.book (
  id BIGSERIAL PRIMARY KEY,
  name CHARACTER VARYING(256) UNIQUE NOT NULL,
  creation_year INTEGER,
  genre_id BIGINT NOT NULL REFERENCES library_app_storage.genre(id),
  author_id BIGINT NOT NULL REFERENCES library_app_storage.author(id)
);

INSERT INTO library_app_storage.genre(name)
    VALUES
      ('роман'),
      ('фэнтези'),
      ('поэма'),
      ('научная фантастика'),
      ('басня'),
      ('драма'),
      ('новелла'),
      ('приключенческий роман'),
      ('исторический роман'),
      ('юмористические рассказы'),
      ('юмористическая повесть'),
      ('роман-эпопея');

INSERT INTO library_app_storage.author(name, date_of_birth)
    VALUES
      ('Морис Дрюон', '1918-04-23'),
      ('Анджей Сапковский', '1948-06-21'),
      ('Данте Алигьери', '1265-05-21'),
      ('Джек Лондон', '1876-01-12'),
      ('Стефан Цвейг', '1881-11-28'),
      ('Иван Крылов', '1769-02-13'),
      ('Вальтер Скотт', '1771-08-15'),
      ('Стендаль', '1783-01-23'),
      ('Джером К. Джером', '1859-05-02'),
      ('Герберт Уэллс', '1886-09-21'),
      ('Лев Толстой', '1828-09-09');

INSERT INTO library_app_storage.book(name, creation_year, genre_id, author_id)
    VALUES
      ('Война и мир', '1869', (SELECT id FROM library_app_storage.genre WHERE name = 'роман-эпопея'), (SELECT id FROM library_app_storage.author WHERE name = 'Лев Толстой')),
      ('Владычица озера', '1998', (SELECT id FROM library_app_storage.genre WHERE name = 'фэнтези'), (SELECT id FROM library_app_storage.author WHERE name = 'Анджей Сапковский')),
      ('Божественная комедия', '1321', (SELECT id FROM library_app_storage.genre WHERE name = 'поэма'), (SELECT id FROM library_app_storage.author WHERE name = 'Данте Алигьери')),
      ('Мартин Иден', '1909', (SELECT id FROM library_app_storage.genre WHERE name = 'драма'), (SELECT id FROM library_app_storage.author WHERE name = 'Джек Лондон')),
      ('Зов предков', '1903', (SELECT id FROM library_app_storage.genre WHERE name = 'приключенческий роман'), (SELECT id FROM library_app_storage.author WHERE name = 'Джек Лондон')),
      ('Анна Каренина', '1877', (SELECT id FROM library_app_storage.genre WHERE name = 'роман'), (SELECT id FROM library_app_storage.author WHERE name = 'Лев Толстой')),
      ('Железный король', '1955', (SELECT id FROM library_app_storage.genre WHERE name = 'исторический роман'), (SELECT id FROM library_app_storage.author WHERE name = 'Морис Дрюон')),
      ('Узница Шато-Гайара', '1955', (SELECT id FROM library_app_storage.genre WHERE name = 'исторический роман'), (SELECT id FROM library_app_storage.author WHERE name = 'Морис Дрюон')),
      ('Шахматная новелла', '1941', (SELECT id FROM library_app_storage.genre WHERE name = 'новелла'), (SELECT id FROM library_app_storage.author WHERE name = 'Стефан Цвейг')),
      ('Письмо незнакомки', '1922', (SELECT id FROM library_app_storage.genre WHERE name = 'новелла'), (SELECT id FROM library_app_storage.author WHERE name = 'Стефан Цвейг')),
      ('Ворона и лисица', '1807', (SELECT id FROM library_app_storage.genre WHERE name = 'басня'), (SELECT id FROM library_app_storage.author WHERE name = 'Иван Крылов')),
      ('Айвенго', '1819', (SELECT id FROM library_app_storage.genre WHERE name = 'исторический роман'), (SELECT id FROM library_app_storage.author WHERE name = 'Вальтер Скотт')),
      ('Красное и чёрное', '1830', (SELECT id FROM library_app_storage.genre WHERE name = 'роман'), (SELECT id FROM library_app_storage.author WHERE name = 'Стендаль')),
      ('Первая книжка праздных мыслей праздного человека', '1886', (SELECT id FROM library_app_storage.genre WHERE name = 'юмористические рассказы'), (SELECT id FROM library_app_storage.author WHERE name = 'Джером К. Джером')),
      ('Трое в лодке, не считая собаки', '1889', (SELECT id FROM library_app_storage.genre WHERE name = 'юмористическая повесть'), (SELECT id FROM library_app_storage.author WHERE name = 'Джером К. Джером')),
      ('Машина времени', '1895', (SELECT id FROM library_app_storage.genre WHERE name = 'научная фантастика'), (SELECT id FROM library_app_storage.author WHERE name = 'Герберт Уэллс')),
      ('Война миров', '1897', (SELECT id FROM library_app_storage.genre WHERE name = 'научная фантастика'), (SELECT id FROM library_app_storage.author WHERE name = 'Герберт Уэллс'));