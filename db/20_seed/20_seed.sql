--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- Data for Name: seminar_type; Type: TABLE DATA; Schema: public; Owner: spring
--

COPY seminar_type (id, foreign_id, name) FROM stdin;
1	\N	VBT
2	\N	NBT
3	\N	Mittelseminar
\.


--
-- Data for Name: method; Type: TABLE DATA; Schema: public; Owner: spring
--

COPY method (id, title, content, seminar_type_id, created_at, modified_at, created_by) FROM stdin;
1	foo	Lorem Ipsum dolor sit amet	1	2017-11-20 14:08:46.191008	2017-11-20 14:08:46.191008	1
2	bar	Summer of 69	1	2017-11-21 11:24:34.385	2017-11-21 11:24:34.385	1
\.


--
-- Data for Name: attachment; Type: TABLE DATA; Schema: public; Owner: spring
--

COPY attachment (id, method_id, content, created_at, modified_at, created_by, modified_by) FROM stdin;
1	1	FooBar McAwesomeville	2017-11-21 10:24:26.879215	2017-11-21 10:24:26.879215	1	\N
3	2	FooBar McAwesomeville	2017-11-21 10:24:52.948511	2017-11-21 10:24:52.948511	1	\N
4	2	Lorem ipsum dolor sit amet	2017-11-21 10:25:06.984856	2017-11-21 10:25:06.984856	1	\N
\.


--
-- Name: attachment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spring
--

SELECT pg_catalog.setval('attachment_id_seq', 4, true);


--
-- Name: method_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spring
--

SELECT pg_catalog.setval('method_id_seq', 2, true);


--
-- Data for Name: method_level; Type: TABLE DATA; Schema: public; Owner: spring
--

COPY method_level (id, name, description) FROM stdin;
1	persönlich	\N
2	abstrakt	\N
3	praktisch	\N
\.


--
-- Name: method_level_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spring
--

SELECT pg_catalog.setval('method_level_id_seq', 3, true);


--
-- Data for Name: method_method_level; Type: TABLE DATA; Schema: public; Owner: spring
--

COPY method_method_level (method_id, method_level_id) FROM stdin;
1	1
2	2
\.


--
-- Data for Name: method_type; Type: TABLE DATA; Schema: public; Owner: spring
--

COPY method_type (id, name, description) FROM stdin;
1	Vortrag	\N
2	Diskussion	\N
3	Stille Diskussion	\N
4	Referat	\N
\.


--
-- Data for Name: method_method_type; Type: TABLE DATA; Schema: public; Owner: spring
--

COPY method_method_type (method_id, method_type_id) FROM stdin;
1	1
1	2
2	3
\.


--
-- Name: method_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spring
--

SELECT pg_catalog.setval('method_type_id_seq', 4, true);


--
-- Name: seminar_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: spring
--

SELECT pg_catalog.setval('seminar_type_id_seq', 3, true);


--
-- PostgreSQL database dump complete
--

