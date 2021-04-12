CREATE TABLE public."estado_carrito"
(
    "uuid_estado_carrito" uuid NOT NULL DEFAULT uuid_generate_v4(),
    "descripcion" character varying NOT NULL,
    "codigo" character varying NOT NULL,
    CONSTRAINT "estado_carrito_pkey" PRIMARY KEY ("uuid_estado_carrito")
);

INSERT INTO "estado_carrito" ("descripcion", "codigo") VALUES ('Pendiente', 'PENDIENTE'), ('Completado', 'COMPLETADO');

COMMENT ON TABLE public."estado_carrito"
    IS 'Diferentes estados de un carrito de compras';