create table family_db
(
    id             bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    family_name    varchar(255),
    nr_of_adults   int         not null,
    nr_of_children int         not null,
    nr_of_infants  int         not null
)