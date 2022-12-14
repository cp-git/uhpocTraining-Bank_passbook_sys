PGDMP                      	    z            bankpassbook    9.6.24    9.6.24     Y           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            Z           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            [           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            \           1262    66425    bankpassbook    DATABASE     ?   CREATE DATABASE bankpassbook WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE bankpassbook;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            ]           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            ^           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            ?            1259    66464    customer    TABLE     ?   CREATE TABLE public.customer (
    cust_seq_id integer NOT NULL,
    bank_id bigint,
    account_number text,
    cust_name text,
    address1 text,
    address2 text,
    city text,
    phone numeric(10,0)
);
    DROP TABLE public.customer;
       public         postgres    false    3            ?            1259    66462    customer_cust_seq_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.customer_cust_seq_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.customer_cust_seq_id_seq;
       public       postgres    false    187    3            _           0    0    customer_cust_seq_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.customer_cust_seq_id_seq OWNED BY public.customer.cust_seq_id;
            public       postgres    false    186            ?            1259    66436    transaction    TABLE     ?   CREATE TABLE public.transaction (
    tran_id bigint NOT NULL,
    cust_seq_id bigint,
    tran_date date,
    tran_details text,
    credit_amt double precision,
    debit_amt double precision
);
    DROP TABLE public.transaction;
       public         postgres    false    3            ?           2604    66467    customer cust_seq_id    DEFAULT     |   ALTER TABLE ONLY public.customer ALTER COLUMN cust_seq_id SET DEFAULT nextval('public.customer_cust_seq_id_seq'::regclass);
 C   ALTER TABLE public.customer ALTER COLUMN cust_seq_id DROP DEFAULT;
       public       postgres    false    187    186    187            V          0    66464    customer 
   TABLE DATA               t   COPY public.customer (cust_seq_id, bank_id, account_number, cust_name, address1, address2, city, phone) FROM stdin;
    public       postgres    false    187          `           0    0    customer_cust_seq_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customer_cust_seq_id_seq', 1, false);
            public       postgres    false    186            T          0    66436    transaction 
   TABLE DATA               k   COPY public.transaction (tran_id, cust_seq_id, tran_date, tran_details, credit_amt, debit_amt) FROM stdin;
    public       postgres    false    185   1       ?           2606    66474 $   customer customer_account_number_key 
   CONSTRAINT     i   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_account_number_key UNIQUE (account_number);
 N   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_account_number_key;
       public         postgres    false    187    187            ?           2606    66472    customer customer_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (cust_seq_id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public         postgres    false    187    187            ?           2606    66443    transaction transaction_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (tran_id);
 F   ALTER TABLE ONLY public.transaction DROP CONSTRAINT transaction_pkey;
       public         postgres    false    185    185            ?           2606    66475 (   transaction transaction_cust_seq_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_cust_seq_id_fkey FOREIGN KEY (cust_seq_id) REFERENCES public.customer(cust_seq_id) NOT VALID;
 R   ALTER TABLE ONLY public.transaction DROP CONSTRAINT transaction_cust_seq_id_fkey;
       public       postgres    false    185    2013    187            V      x?????? ? ?      T      x?????? ? ?     