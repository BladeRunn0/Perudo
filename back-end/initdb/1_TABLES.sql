create table students
(
    id SERIAL PRIMARY KEY,
    first_name TEXT not null,
    last_name TEXT not null,
    birthdate date null,
    major_id int null,
    image bytea null
);

create table majors
(
    id SERIAL PRIMARY KEY,
    name TEXT not null,
    description TEXT not null
);

create table courses
(
    id SERIAL PRIMARY KEY,
    name TEXT not null,
    hours int not null
);

create table student_course
(
    id SERIAL PRIMARY KEY,
    student_id int not null,
    course_id int not null
);

create table players
(
    id SERIAL PRIMARY KEY,
    first_name TEXT not null,
    last_name TEXT not null,
    active_dice_number int null
);

create table dices
(
  id SERIAL PRIMARY KEY,
  player_id SERIAL not null,
  dice_value TEXT not null
);

create table scores
(
    id SERIAL PRIMARY KEY,
    score int not null
);

create table player_score
(
    id SERIAL PRIMARY KEY,
    player_id SERIAL not null,
    score_id SERIAL not null
)