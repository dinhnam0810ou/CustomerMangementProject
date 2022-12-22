CREATE TABLE public.customer (
    id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    "name" varchar(50) NOT NULL,
    gender varchar(50) NOT NULL,
    phone_number varchar(10) NOT NULL,
    address varchar(50) NULL,
    email varchar(50) NULL,
    point int4 NULL,
    useticketfree int4 NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

-- public.movie definition

-- Drop table

-- DROP TABLE public.movie;

CREATE TABLE public.movie (
    id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    "name" varchar(50) NOT NULL,
    timetotal int4 NULL,
    image varchar(250) NULL,
    price float8 NULL,
    CONSTRAINT movie_pkey PRIMARY KEY (id)
);

-- public.ticket_order definition

-- Drop table

-- DROP TABLE public.ticket_order;

CREATE TABLE public.ticket_order (
    id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    orderdate date NULL,
    quantity int4 NULL,
    unitprice float8 NULL,
    customerid int4 NULL,
    movieid int4 NULL,
    CONSTRAINT ticket_order_pkey PRIMARY KEY (id)
);


-- public.ticket_order foreign keys

ALTER TABLE public.ticket_order ADD CONSTRAINT fk_customer FOREIGN KEY (customerid) REFERENCES public.customer(id);
ALTER TABLE public.ticket_order ADD CONSTRAINT fk_movie FOREIGN KEY (movieid) REFERENCES public.movie(id);
