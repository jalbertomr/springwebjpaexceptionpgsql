-- Table: public.person_jpa

-- DROP TABLE public.person_jpa;

CREATE TABLE public.person_jpa
(
    id bigint NOT NULL,
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