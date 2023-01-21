create table family_member_db
(
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    family_id bigint not null,
    given_name varchar(255),
    family_name varchar(255),
    age int not null
)