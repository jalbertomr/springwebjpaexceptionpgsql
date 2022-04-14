-- Table: public.person_jpa

DROP TABLE if exists public.person_jpa cascade;

DROP SEQUENCE if exists public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence;

CREATE TABLE public.person_jpa
(
    id bigint NOT NULL DEFAULT nextval('hibernate_sequence'::regclass),
    age integer NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "integer" integer,
    last_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT person_jpa_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.person_jpa
    OWNER to postgres;