PGDMP         #            	    z            bankpassbook    9.6.24    9.6.24     ^           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            _           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            `           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            a           1262    66425    bankpassbook    DATABASE     �   CREATE DATABASE bankpassbook WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE bankpassbook;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            b           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            c           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    74676    customer    TABLE     �   CREATE TABLE public.customer (
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
       public         postgres    false    3            �            1259    74674    customer_cust_seq_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customer_cust_seq_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.customer_cust_seq_id_seq;
       public       postgres    false    188    3            d           0    0    customer_cust_seq_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.customer_cust_seq_id_seq OWNED BY public.customer.cust_seq_id;
            public       postgres    false    187            �            1259    74643    transaction    TABLE     �   CREATE TABLE public.transaction (
    tran_id integer NOT NULL,
    cust_seq_id bigint,
    tran_date date,
    tran_details text,
    credit_amt double precision,
    debit_amt double precision
);
    DROP TABLE public.transaction;
       public         postgres    false    3            �            1259    74641    transaction_tran_id_seq    SEQUENCE     �   CREATE SEQUENCE public.transaction_tran_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.transaction_tran_id_seq;
       public       postgres    false    186    3            e           0    0    transaction_tran_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.transaction_tran_id_seq OWNED BY public.transaction.tran_id;
            public       postgres    false    185            �           2604    74679    customer cust_seq_id    DEFAULT     |   ALTER TABLE ONLY public.customer ALTER COLUMN cust_seq_id SET DEFAULT nextval('public.customer_cust_seq_id_seq'::regclass);
 C   ALTER TABLE public.customer ALTER COLUMN cust_seq_id DROP DEFAULT;
       public       postgres    false    187    188    188            �           2604    74646    transaction tran_id    DEFAULT     z   ALTER TABLE ONLY public.transaction ALTER COLUMN tran_id SET DEFAULT nextval('public.transaction_tran_id_seq'::regclass);
 B   ALTER TABLE public.transaction ALTER COLUMN tran_id DROP DEFAULT;
       public       postgres    false    185    186    186            [          0    74676    customer 
   TABLE DATA               t   COPY public.customer (cust_seq_id, bank_id, account_number, cust_name, address1, address2, city, phone) FROM stdin;
    public       postgres    false    188          f           0    0    customer_cust_seq_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customer_cust_seq_id_seq', 39, true);
            public       postgres    false    187            Y          0    74643    transaction 
   TABLE DATA               k   COPY public.transaction (tran_id, cust_seq_id, tran_date, tran_details, credit_amt, debit_amt) FROM stdin;
    public       postgres    false    186   @       g           0    0    transaction_tran_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.transaction_tran_id_seq', 36, true);
            public       postgres    false    185            �           2606    74686 $   customer customer_account_number_key 
   CONSTRAINT     i   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_account_number_key UNIQUE (account_number);
 N   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_account_number_key;
       public         postgres    false    188    188            �           2606    74688    customer customer_phone_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_phone_key UNIQUE (phone);
 E   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_phone_key;
       public         postgres    false    188    188            �           2606    74684    customer customer_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (cust_seq_id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public         postgres    false    188    188            �           2606    74651    transaction transaction_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (tran_id);
 F   ALTER TABLE ONLY public.transaction DROP CONSTRAINT transaction_pkey;
       public         postgres    false    186    186            [     x����R�0���S��O��f���+7W)Iji;��mp��;����$�1����������P!�`]F�[�����>4%hm�����۽���^�+�Cs��e�r���yu�1v�*sϭ�v[y�:c�v��U���ی->��#�}h>�7n`v]'e�T�1*$�tb):�qr���X%�q)�ݦ�8�p9~�t4Z
F����g��5���o���X���re�|�/��c���6=$��J]���)�B�

l�2�kצO����S�5y�'�� �Ẹ      Y   �  x����n�0E���P��LU@(�TU�&T4 A!-���8v�A���|�g<��8�=���e�!_��?�DڐK����P\�����0�b�]eU��qc 8�:8���[��#uTp������nF�pv�s�B��Q0Ɋ�:<���|=�W'��G��_э>�Җ�G�^wƎe>��0�$a��8�p,�Y]�!�m�l�����Ϧ.���<?hJ��C��W���X �y]�0�m�M�\cݹX} �|i oA�nN�)H�DK��4��9�_Gq�F��`�K���04�rW#4{��td�b�ܮ�s_��>���F�.A�|��`̓�$L�4�� }�?hI�?Ϩ2�j;z�[{T�lg!��<�WO��	�o~7��A�E��{�^�Ķ&τ�_c�1     