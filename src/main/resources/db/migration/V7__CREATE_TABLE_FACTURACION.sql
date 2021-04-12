CREATE TABLE public."facturacion"
(
    "uuid_facturacion" uuid NOT NULL DEFAULT uuid_generate_v4(),
    "carrito" uuid NOT NULL,
    "subtotal" numeric NOT NULL,
    "descuento" numeric NOT NULL,
    "total" numeric NOT NULL,
    CONSTRAINT "facturacion_pkey" PRIMARY KEY ("uuid_facturacion"),
    CONSTRAINT "facturacion_carrito_fkey" FOREIGN KEY ("carrito")
        REFERENCES public."carrito" ("uuid_carrito") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE public."facturacion"
    IS 'Registro de las facturas pagadas sobre los carritos de compra';