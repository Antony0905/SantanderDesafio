create database santander;

\c santander

CREATE TABLE IF NOT EXISTS public.sistema_credenciado
(
    id integer NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT sistema_credenciado_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.cliente
(
    id integer NOT NULL,
    codigo_usuario integer NOT NULL,
    nome character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.perfis_sistema_credenciado
(
    sistema_credenciado_id integer NOT NULL,
    perfis integer NOT NULL
);

CREATE TABLE IF NOT EXISTS public.perfis_cliente
(
    cliente_id integer NOT NULL,
    perfis_cliente integer NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS public.sistema_credenciado_generator;

CREATE SEQUENCE IF NOT EXISTS public.cliente_generator;

INSERT INTO sistema_credenciado VALUES((SELECT NEXTVAL('sistema_credenciado_generator')),'santander@santander.com','santander','$2a$10$La9q6NxVEybwpX/FuG7sXuVEvhnhzcQNhOIXPo7wsmDOEGL8dDzPG');

INSERT INTO public.perfis_sistema_credenciado(sistema_credenciado_id, perfis) VALUES (1,1);

INSERT INTO public.cliente(id, codigo_usuario, email, nome, senha) VALUES ((SELECT NEXTVAL('cliente_generator')),1,'matheus@matheus.com','matheus','$2a$10$ongwvYxZZKrg.mrFFJhSseY9D6dXrT0F59uXYLhEcUs8nMSSdWtD.');
		
INSERT INTO public.perfis_cliente(cliente_id, perfis_cliente) VALUES (1,2);




