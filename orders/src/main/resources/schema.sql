DROP SCHEMA IF EXISTS "order" CASCADE;

CREATE SCHEMA "order";

CREATE TABLE "order".orders (
    id uuid PRIMARY KEY NOT NULL ,
    customer_id uuid NOT NULL,
    tracking_number character varying COLLATE pg_catalog."default" NOT NULL,
    price numeric(10,2) NOT NULL,
    status character varying NOT NULL,
    failure_messages character varying COLLATE pg_catalog."default",
    CONSTRAINT order_status_chk CHECK (status IN ('PENDING', 'PAID', 'APPROVED', 'CANCELLED', 'CANCELLING'))
);

CREATE TABLE "order".order_items(
    id uuid NOT NULL,
    order_id uuid NOT NULL,
    product_id uuid NOT NULL,
    price_per_unit numeric(10,2) NOT NULL,
    quantity integer NOT NULL,
    subtotal numeric(10,2) NOT NULL,
    CONSTRAINT order_items_pkey PRIMARY KEY (id, order_id)
);

ALTER TABLE "order".order_items
    ADD CONSTRAINT "FK_ORDER_ID" FOREIGN KEY (order_id)
    REFERENCES "order".orders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;
