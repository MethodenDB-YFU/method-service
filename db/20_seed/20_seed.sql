--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 10.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = mdb_data, pg_catalog;

--
-- Data for Name: mu_user; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY mu_user (mu_id, mu_role) FROM stdin;
aa40d8c0-e705-11e7-80c1-9a214cf093ae	EDITOR
\.


--
-- Data for Name: st_seminar_type; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY st_seminar_type (st_id, st_foreign_id, st_name) FROM stdin;
d0ea49d5-49ed-472d-a63d-dcd2f2b4bf5d	\N	VBT
ef410708-4cab-4047-9647-27dc1384267f	\N	OWO
27f5085e-23d8-4e3b-a917-8dfd7c36a76c	\N	NBT
44fc3b8c-814c-4094-bb12-a1553de96a27	\N	MS
011908e4-dd14-484e-8577-f553c0403000	\N	ReEntry
\.


--
-- Data for Name: mm_method; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY mm_method (mm_id, mm_title, mm_content, mm_seminar_type_id, mm_created_at, mm_modified_at, mm_modified_by, mm_created_by) FROM stdin;
3465daed-ec46-4b95-bae1-7b048b138569	Lorem Ipsum	Et maiores a magni doloribus. Dolor dolores dolores illum facere dolor itaque incidunt molestiae. Consequuntur velit asperiores natus iste dignissimos. Nobis voluptatibus nam modi illum voluptas maxime. Unde consequatur tempora dolorum sunt sint aspernatur beatae minus. Error qui placeat aut libero pariatur maxime. Aliquid nostrum veniam ut. Alias ut voluptatem consequuntur dolores magnam inventore recusandae. Deserunt mollitia nostrum illum. Velit porro voluptatem est. Doloribus explicabo itaque distinctio. Qui sit aut aut dicta. In voluptas nisi ipsum est et a. Vitae hic reprehenderit doloremque aspernatur enim labore accusantium ducimus. Consequatur fugit provident et voluptas. Aut itaque totam dolorum. Sequi deserunt dolorem magni sequi. Repellendus cupiditate unde quia aut temporibus ut et. Ducimus quod quae ipsa aut illum minus. Et amet sit libero eos et quia laborum. Natus deleniti odit in quo ut reprehenderit sit. Consequatur dignissimos totam voluptas reprehenderit facere. Voluptate dolorem libero quis. Quis qui suscipit nisi id quos. Magni tempora velit est enim quia odio natus. Rem sapiente veritatis fugiat ut. Quo molestiae a ea et unde ut voluptas vero. Corporis nulla placeat soluta eum ipsam non culpa. Voluptatem ipsam impedit qui eius alias vitae et. Possimus quos quas quas. Sed laboriosam cum aperiam libero aliquid quo dolores nihil. Odit occaecati illum fuga consequatur quia. Non accusamus quis necessitatibus odio et odio. Deleniti quidem adipisci sit qui corporis. Deserunt quaerat praesentium quaerat consequuntur atque. Ut velit repudiandae sit laborum dolorum quasi dolorem commodi. Est molestias inventore similique. Voluptas sit aut fugit dolores voluptatum dignissimos nobis inventore. Tempora in mollitia voluptates ut est. Dolore amet nemo velit dolor. Veritatis molestiae maxime ut officia. Fugiat non quae aliquam.	d0ea49d5-49ed-472d-a63d-dcd2f2b4bf5d	2018-03-11 20:52:27.908	\N	\N	aa40d8c0-e705-11e7-80c1-9a214cf093ae
446d7fbf-a952-494b-a43d-946da2b46770	Any Method	Any Content	ef410708-4cab-4047-9647-27dc1384267f	2018-01-09 15:44:05.865	2018-03-11 20:59:36.595	aa40d8c0-e705-11e7-80c1-9a214cf093ae	aa40d8c0-e705-11e7-80c1-9a214cf093ae
7b5f7ecd-6b24-4320-8f25-85b487a21d5e	Any Method	Any Content	ef410708-4cab-4047-9647-27dc1384267f	2018-01-08 23:14:34.908	2018-03-11 21:04:09.151	aa40d8c0-e705-11e7-80c1-9a214cf093ae	\N
11e556d6-728e-45ec-9407-7f2cefc50eea	Any Method	Any Content	ef410708-4cab-4047-9647-27dc1384267f	2018-01-08 23:16:55.812	2018-03-11 21:05:11.794	aa40d8c0-e705-11e7-80c1-9a214cf093ae	aa40d8c0-e705-11e7-80c1-9a214cf093ae
\.


--
-- Data for Name: ma_attachment; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY ma_attachment (ma_id, ma_method_id, ma_content, ma_created_at, ma_modified_at, ma_created_by, ma_modified_by) FROM stdin;
b9efc229-ce26-43b0-a2cc-c93e6b87dc66	446d7fbf-a952-494b-a43d-946da2b46770	Any Attachment	2018-01-09 14:44:05.88	\N	aa40d8c0-e705-11e7-80c1-9a214cf093ae	\N
7a0b8492-e1bb-4467-bf17-fea132d31c0c	446d7fbf-a952-494b-a43d-946da2b46770	Some Attachment	2018-01-09 14:44:05.881	\N	aa40d8c0-e705-11e7-80c1-9a214cf093ae	\N
9a64fea3-c995-4933-858e-7d90757a8a17	3465daed-ec46-4b95-bae1-7b048b138569	Ad magnam ea sequi. Libero et sed tenetur rerum voluptatum nesciunt delectus illo. Reprehenderit nam sunt maiores omnis et dolorem nobis. Et maiores a magni doloribus. Dolor dolores dolores illum facere dolor itaque incidunt molestiae. Consequuntur velit asperiores natus iste dignissimos.<br> Nobis voluptatibus nam modi illum voluptas maxime. Unde consequatur tempora dolorum sunt sint aspernatur beatae minus. Error qui placeat aut libero pariatur maxime. Aliquid nostrum veniam ut.<p>Alias ut voluptatem consequuntur dolores magnam inventore recusandae. Deserunt mollitia nostrum illum. Velit porro voluptatem est. Doloribus explicabo itaque distinctio. Qui sit aut aut dicta.<br>In voluptas nisi ipsum est et a. Vitae hic reprehenderit doloremque aspernatur enim labore accusantium ducimus. Consequatur fugit provident et voluptas. Aut itaque totam dolorum. Sequi deserunt dolorem magni sequi.</p>Repellendus cupiditate unde quia aut temporibus ut et. Ducimus quod quae ipsa aut illum minus. Et amet sit libero eos et quia laborum. Natus deleniti odit in quo ut reprehenderit sit.	2018-03-11 20:52:27.908	\N	aa40d8c0-e705-11e7-80c1-9a214cf093ae	\N
a0fa253c-ae2a-4ac1-a3cd-a0947fe75eb3	3465daed-ec46-4b95-bae1-7b048b138569	Et maiores a magni doloribus. Dolor dolores dolores illum facere dolor itaque incidunt molestiae. Consequuntur velit asperiores natus iste dignissimos. Nobis voluptatibus nam modi illum voluptas maxime. Unde consequatur tempora dolorum sunt sint aspernatur beatae minus. Error qui placeat aut libero pariatur maxime. Aliquid nostrum veniam ut. Alias ut voluptatem consequuntur dolores magnam inventore recusandae. Deserunt mollitia nostrum illum. Velit porro voluptatem est. Doloribus explicabo itaque distinctio. Qui sit aut aut dicta. In voluptas nisi ipsum est et a. Vitae hic reprehenderit doloremque aspernatur enim labore accusantium ducimus. Consequatur fugit provident et voluptas. Aut itaque totam dolorum. Sequi deserunt dolorem magni sequi. Repellendus cupiditate unde quia aut temporibus ut et. Ducimus quod quae ipsa aut illum minus. Et amet sit libero eos et quia laborum. Natus deleniti odit in quo ut reprehenderit sit. Consequatur dignissimos totam voluptas reprehenderit facere. Voluptate dolorem libero quis. Quis qui suscipit nisi id quos. Magni tempora velit est enim quia odio natus. Rem sapiente veritatis fugiat ut. Quo molestiae a ea et unde ut voluptas vero. Corporis nulla placeat soluta eum ipsam non culpa. Voluptatem ipsam impedit qui eius alias vitae et. Possimus quos quas quas. Sed laboriosam cum aperiam libero aliquid quo dolores nihil. Odit occaecati illum fuga consequatur quia. Non accusamus quis necessitatibus odio et odio. Deleniti quidem adipisci sit qui corporis. Deserunt quaerat praesentium quaerat consequuntur atque. Ut velit repudiandae sit laborum dolorum quasi dolorem commodi. Est molestias inventore similique. Voluptas sit aut fugit dolores voluptatum dignissimos nobis inventore. Tempora in mollitia voluptates ut est. Dolore amet nemo velit dolor. Veritatis molestiae maxime ut officia. Fugiat non quae aliquam.	2018-03-11 20:52:27.909	\N	aa40d8c0-e705-11e7-80c1-9a214cf093ae	\N
\.


--
-- Data for Name: ml_method_level; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY ml_method_level (ml_id, ml_name, ml_description) FROM stdin;
9053311a-4b82-4b77-9c8f-ee1b6e986bfe	theoretisch	Foo Bar der theoretisch ist.
4cc10d96-24dd-420d-9a4e-ff4c5df57d1b	persönlich	\N
\.


--
-- Data for Name: mm_method_method_level; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY mm_method_method_level (mm_method_id, mm_method_level_id) FROM stdin;
7b5f7ecd-6b24-4320-8f25-85b487a21d5e	9053311a-4b82-4b77-9c8f-ee1b6e986bfe
11e556d6-728e-45ec-9407-7f2cefc50eea	9053311a-4b82-4b77-9c8f-ee1b6e986bfe
446d7fbf-a952-494b-a43d-946da2b46770	9053311a-4b82-4b77-9c8f-ee1b6e986bfe
446d7fbf-a952-494b-a43d-946da2b46770	4cc10d96-24dd-420d-9a4e-ff4c5df57d1b
3465daed-ec46-4b95-bae1-7b048b138569	4cc10d96-24dd-420d-9a4e-ff4c5df57d1b
\.


--
-- Data for Name: mt_method_type; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY mt_method_type (mt_id, mt_name, mt_description) FROM stdin;
09f0dd76-3dc5-44e9-bfc0-c14e0fa2ea9c	Film	\N
9a03a19f-22ad-489d-b3ee-e006e9237398	Diskussion	\N
\.


--
-- Data for Name: mm_method_method_type; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY mm_method_method_type (mm_method_id, mm_method_type_id) FROM stdin;
7b5f7ecd-6b24-4320-8f25-85b487a21d5e	09f0dd76-3dc5-44e9-bfc0-c14e0fa2ea9c
11e556d6-728e-45ec-9407-7f2cefc50eea	09f0dd76-3dc5-44e9-bfc0-c14e0fa2ea9c
446d7fbf-a952-494b-a43d-946da2b46770	09f0dd76-3dc5-44e9-bfc0-c14e0fa2ea9c
3465daed-ec46-4b95-bae1-7b048b138569	9a03a19f-22ad-489d-b3ee-e006e9237398
\.


--
-- Data for Name: sg_seminar_goal; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY sg_seminar_goal (sg_id, sg_name, sg_description, sg_seminar_type_id) FROM stdin;
59d2d4d3-58fb-474d-8f44-f36b9d99730b	Some VBT Goal	Any VBT Goal	d0ea49d5-49ed-472d-a63d-dcd2f2b4bf5d
70a5de72-a5e0-4d96-9534-cd8ffdb78590	Kommunikation		d0ea49d5-49ed-472d-a63d-dcd2f2b4bf5d
07d531f8-fcc8-4538-9836-594141f47f3c	Anpassung		d0ea49d5-49ed-472d-a63d-dcd2f2b4bf5d
f8590d93-f6b5-4701-8578-bf27a9bac290	Kultur		d0ea49d5-49ed-472d-a63d-dcd2f2b4bf5d
\.


--
-- Data for Name: mm_method_seminar_goal; Type: TABLE DATA; Schema: mdb_data; Owner: methodendb
--

COPY mm_method_seminar_goal (mm_method_id, mm_seminar_goal_id) FROM stdin;
7b5f7ecd-6b24-4320-8f25-85b487a21d5e	59d2d4d3-58fb-474d-8f44-f36b9d99730b
11e556d6-728e-45ec-9407-7f2cefc50eea	59d2d4d3-58fb-474d-8f44-f36b9d99730b
446d7fbf-a952-494b-a43d-946da2b46770	59d2d4d3-58fb-474d-8f44-f36b9d99730b
3465daed-ec46-4b95-bae1-7b048b138569	07d531f8-fcc8-4538-9836-594141f47f3c
3465daed-ec46-4b95-bae1-7b048b138569	59d2d4d3-58fb-474d-8f44-f36b9d99730b
\.


--
-- PostgreSQL database dump complete
--

