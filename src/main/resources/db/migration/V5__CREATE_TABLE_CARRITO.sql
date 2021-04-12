CREATE SEQUENCE public."carrito_id_carrito_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public."carrito"
(
    "uuid_carrito" uuid NOT NULL DEFAULT uuid_generate_v4(),
    "id_carrito" bigint NOT NULL DEFAULT nextval('"carrito_id_carrito_seq"'::regclass),
    "creacion_carrito" timestamp without time zone NOT NULL DEFAULT now(),
    "estado_carrito" uuid NOT NULL,
    CONSTRAINT "carrito_pkey" PRIMARY KEY ("uuid_carrito"),
    CONSTRAINT "carrito_estado_carrito_fkey" FOREIGN KEY ("estado_carrito")
        REFERENCES public."estado_carrito" ("uuid_estado_carrito") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE public."carrito"
    IS 'Registro de los carritos de compras';

COMMENT ON COLUMN public."carrito"."id_carrito"
    IS 'External ID';

COMMENT ON COLUMN public."carrito"."creacion_carrito"
    IS 'Fecha de creacion del carrito de compras';