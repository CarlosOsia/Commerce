CREATE TABLE public."carrito_productos"
(
    "uuid_carrito_producto" uuid NOT NULL DEFAULT uuid_generate_v4(),
    "carrito" uuid NOT NULL,
    "producto" uuid NOT NULL,
    "cantidad" numeric NOT NULL DEFAULT 1,
    CONSTRAINT "carrito_productos_pkey" PRIMARY KEY ("uuid_carrito_producto"),
    CONSTRAINT "carrito_productos_carrito_fkey" FOREIGN KEY ("carrito")
        REFERENCES public."carrito" ("uuid_carrito") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "carrito_productos_producto_fkey" FOREIGN KEY ("producto")
        REFERENCES public."productos" ("uuid_producto") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);