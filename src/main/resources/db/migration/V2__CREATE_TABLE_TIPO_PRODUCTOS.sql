CREATE TABLE public."tipo_productos"
(
    "uuid_tipo_producto" uuid NOT NULL DEFAULT uuid_generate_v4(),
    "descripcion" character varying NOT NULL,
    "codigo" character varying NOT NULL,
    CONSTRAINT "tipo_productos_pkey" PRIMARY KEY ("uuid_tipo_producto")
);

COMMENT ON TABLE public."tipo_productos"
    IS 'Tipos de productos existentes';

INSERT INTO "tipo_productos" ("descripcion", "codigo") VALUES ('Simple', 'SIMPLE'), ('Descuento', 'DESCUENTO');

