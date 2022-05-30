-- Table: public.personjpa

DROP TABLE if exists public.personjpa cascade;

DROP SEQUENCE if exists public.personjpa_sequence cascade;

CREATE SEQUENCE public.personjpa_sequence;

CREATE TABLE public.personjpa
(
    id bigint NOT NULL DEFAULT nextval('personjpa_sequence'::regclass),
    age integer NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "integer" integer,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT personjpa_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.personjpa
    OWNER to postgres;