SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS movies (
  id int PRIMARY KEY auto_increment,
  title VARCHAR,
  director VARCHAR,
  releaseDate VARCHAR,
  website VARCHAR,
  image VARCHAR,
);

CREATE TABLE IF NOT EXISTS genres (
  id int PRIMARY KEY auto_increment,
  name VARCHAR
);

CREATE TABLE IF NOT EXISTS reviews (
  id int PRIMARY KEY auto_increment,
  writtenby VARCHAR,
  rating VARCHAR,
  createdat TIMESTAMP,
  content VARCHAR,
  movieid INTEGER
);

CREATE TABLE IF NOT EXISTS movies_genres (
 id int PRIMARY KEY auto_increment,
 movieId INTEGER,
 genreId INTEGER
);