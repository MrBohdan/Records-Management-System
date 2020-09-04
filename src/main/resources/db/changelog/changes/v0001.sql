CREATE EXTENSION IF NOT EXISTS hstore;

CREATE TABLE IF NOT EXISTS "record" (
    usreou bigint NOT NULL,
    shares_amount bigint NOT NULL,
    comment varchar(255) NOT NULL,
    par_value double precision NOT NULL,
    pay_date timestamp without time zone NOT NULL,
    status varchar(255) NOT NULL,
    total_par_value double precision NOT NULL,
    PRIMARY KEY (usreou)
);

CREATE TABLE IF NOT EXISTS audit (
    audit_id serial NOT NULL,
    table_name text NOT NULL,
    user_name text NOT NULL,
    action_timestamp timestamp NOT NULL default current_timestamp,
    action text NOT NULL CHECK (action IN ('insert','delete','update')),
    usreou bigint NOT NULL REFERENCES record(usreou),
    old_values hstore,
    new_values hstore,
    updated_cols text[],
    query text,
    PRIMARY KEY (audit_id)
);

CREATE OR REPLACE FUNCTION if_modified_func() RETURNS TRIGGER AS $body$
BEGIN
    IF tg_op = 'UPDATE' THEN
        INSERT INTO audit (table_name, user_name, action, usreou, old_values, new_values, updated_cols, query)
        VALUES (tg_table_name::text, current_user::text, 'update', new.usreou, hstore(old.*), hstore(new.*),
               akeys(hstore(new.*) - hstore(old.*)), current_query());
        return new;
    ELSIF tg_op = 'DELETE' THEN
        INSERT INTO audit (table_name, user_name, action, usreou, old_values, query)
        VALUES (tg_table_name::text, current_user::text, 'delete', old.usreou, hstore(old.*), current_query());
        return old;
    ELSIF tg_op = 'INSERT' THEN
        INSERT INTO audit (table_name, user_name, action, usreou, new_values, query)
        VALUES (tg_table_name::text, current_user::text, 'insert', new.usreou, hstore(new.*), current_query());
        return new;
    END IF;
END;
$body$
language plpgsql;

CREATE TRIGGER record_audit AFTER INSERT OR UPDATE OR DELETE ON record FOR EACH ROW EXECUTE PROCEDURE if_modified_func();