CREATE TABLE public."productos"
(
    "uuid_producto" uuid NOT NULL DEFAULT uuid_generate_v4(),
    "tipo_producto" uuid NOT NULL,
    "sku" character varying NOT NULL,
    "precio" numeric NOT NULL,
    "nombre" character varying NOT NULL,
    "descripcion" character varying NOT NULL,
    "creacion_producto" timestamp without time zone NOT NULL DEFAULT now(),
    "activo" boolean NOT NULL DEFAULT true,
    CONSTRAINT "productos_pkey" PRIMARY KEY ("uuid_producto"),
    CONSTRAINT "productos_tipo_producto_fkey" FOREIGN KEY ("tipo_producto")
        REFERENCES public."tipo_productos" ("uuid_tipo_producto") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE public."productos"
    IS 'Registro de los productos';

COMMENT ON COLUMN public."productos"."creacion_producto"
    IS 'Fecha de creacion del producto';

CREATE UNIQUE INDEX index_productos_sku_tipo
    ON public."productos" USING btree
    ("sku" ASC NULLS LAST, "tipo_producto" ASC NULLS LAST);