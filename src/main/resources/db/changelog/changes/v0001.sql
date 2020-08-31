create table "record" (
   usreou bigint NOT NULL,
    amount bigint NOT NULL,
    comment varchar(255) NOT NULL,
    par_value double precision NOT NULL,
    pay_date timestamp without time zone NOT NULL,
    status varchar(255) NOT NULL,
    total_par_value double precision NOT NULL,
    PRIMARY KEY (usreou)
);