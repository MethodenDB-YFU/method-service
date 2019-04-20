--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: mu_user; Type: TABLE DATA; Schema: mdb_data; Owner: methods
--

COPY mdb_data.mu_user (mu_id, mu_role) FROM stdin;
00000000-0000-0000-0000-000000000000	ADMIN
\.


--
-- Data for Name: mm_method; Type: TABLE DATA; Schema: mdb_data; Owner: methods
--

COPY mdb_data.mm_method (mm_id, mm_title, mm_content, mm_seminar_type_id, mm_created_at, mm_modified_at, mm_modified_by, mm_created_by) FROM stdin;
8a059932-a32b-43df-a049-1bf982b2c111	Blitzlicht	{"time":1555755002132,"blocks":[{"type":"paragraph","data":{"text":"Das Blitzlicht oder die Statementrunde ist eine „Momentaufnahme“ in einer Gruppe. Jeder äußert sich kurz und knapp zu einer Frage, ohne darüber zu diskutieren. Es ist eine kleine, aber feine Methode, um ein Meinungsbild in einer Gruppe zu erheben."}},{"type":"header","data":{"text":"So funktioniert es","level":2}},{"type":"paragraph","data":{"text":"Es wird kurz die Fragestellung erläutert und der Grund warum gefragt wird Die Regeln des Blitzlichts werden erklärt:"}},{"type":"list","data":{"style":"unordered","items":["Max. ein bis zwei Sätze.","Ohne Diskussion","Der Reihe nach"]}},{"type":"paragraph","data":{"text":"Die Teilnehmer wird eine genügend lange Nachdenkpause eingeräumt. Die Qualität der Beiträge steht und fällt mit der Pause zwischen Fragestellung und Start. Einer fängt an, dann geht es zügig reihum."}},{"type":"paragraph","data":{"text":"Mit dem Blitzlicht lässt sich fragen nach"}},{"type":"list","data":{"style":"unordered","items":["Erwartungen","Positiven Erfahrungen zum Thema<br>","Der persönlichen Meinung zu einem Vorschlag<br>","Offenen Fragen, die noch geklärt werden sollen<br>","Wünschen und Vorschlägen für das weitere Vorgehen<br>","Der Stimmung in diesem Moment<br>","Kurzem Feedback<br>"]}},{"type":"header","data":{"text":"Variationen","level":2}},{"type":"paragraph","data":{"text":"Je nach Situation und Fragestellung kann man zulassen, dass einzelne, die im Moment nichts sagen wollen, mit „weiter“ an den nächsten in der Reihe weitergeben. In schwierigen Situationen ist es aber sinnvoller, dass sich alle Teilnehmer äußern."}}],"version":"2.12.4"}	f2c7c880-4158-11e8-842f-0ed5f89f718b	2019-04-20 10:10:28.124055	\N	\N	00000000-0000-0000-0000-000000000000
c5ad6812-71e9-45b1-9e49-32d948e91f9e	Brief an sich selbst	{"time":1555755047569,"blocks":[{"type":"paragraph","data":{"text":"Der Brief an sich selbst ist eine Methode zur Selbstreflexion und zur Überprüfung der eigenen Entwicklung. Die Teilnehmer schreiben einen Brief an sich selbst, in dem sie sich mit sich selbst, ihren Gefühlen und ihren Plänen auseinandersetzen. Der Brief wird verschlossen und mit der Heimatanschrift versehen. Er wird den Teilnehmern dann nach einiger Zeit zugestellt."}}],"version":"2.12.4"}	f2c7c880-4158-11e8-842f-0ed5f89f718b	2019-04-20 10:19:03.174606	\N	\N	00000000-0000-0000-0000-000000000000
9adacb76-d7b4-41e0-b786-9444e638d5cf	Ikarus Parabel	{"time":1555755708529,"blocks":[{"type":"paragraph","data":{"text":"Ikarus bekam von seinem Vater Flügel aus Wachs und Federn gebaut um über das Meer aus dem Labyrinth zu fliegen. Die Schwierigkeit war dabei, nicht zu hoch/nah an der Sonne zu fliegen, denn sonst würde der Wachs, der die Federn zusammenhielt, schmelzen (Absturz), aber auch nicht zu nah am Wasser zu bleiben, da sich sonst die Federn mit der Feuchtigkeit vollsögen. Dadurch wäre das bewegen der Flügel zu schwer um weit zu fliegen.&nbsp;"}},{"type":"paragraph","data":{"text":"Ikarus ist übrigens abgestürzt, aber das tut hier nix zur Sache :-)&nbsp;"}}],"version":"2.12.4"}	f2c7c880-4158-11e8-842f-0ed5f89f718b	2019-04-20 10:22:23.343149	\N	\N	00000000-0000-0000-0000-000000000000
d762a048-c4df-4b03-93da-f88c1048b3e2	Nein Spirale	{"time":1555755762383,"blocks":[{"type":"paragraph","data":{"text":"Alle Kids stellen sich in einem Kreis auf. Der_die Teamer_in sagt leise aber bestimmt zum_r Nachbar_in „Nein“. Diese_r gibt das „Nein“ jeweils lauter und bestimmter weiter (es kann auch geschrien und gebrüllt werden, es kommt auf Emotionen an. In der zweiten Runde wird das „Nein“ nur weiter gegeben, wenn der Empfänger von seiner Bestimmtheit überzeugt ist (laut/aggressiv/ablehnen/überzeugend genug). Wenn der Grad der Bestimmtheit und Lautstärke nicht mehr zu steigern ist, kehrt man zu einem leisen „Nein“ zurück und beginnt die Steigerung erneut. In der dritten Runde werden statt eines „Nein“ andere Möglichkeiten der Weigerung ausprobiert, vielleicht auch in einer Fremdsprache. Möglichkeiten: „Hau ab“, „Ich will nicht“, „Lass mich in Ruhe“. Hier kommt es nicht auf die Lautstärke an, sondern auf das Austesten der sprachlichen Möglichkeiten. Jeder kann sagen was er/sie möchte."}}],"version":"2.12.4"}	f2c7c880-4158-11e8-842f-0ed5f89f718b	2019-04-20 10:23:16.312756	\N	\N	00000000-0000-0000-0000-000000000000
\.


--
-- Data for Name: ma_attachment; Type: TABLE DATA; Schema: mdb_data; Owner: methods
--

COPY mdb_data.ma_attachment (ma_id, ma_method_id, ma_title, ma_content, ma_created_at, ma_modified_at, ma_created_by, ma_modified_by) FROM stdin;
6144d0ef-bf7e-4373-89c0-4b7e34a1d202	c5ad6812-71e9-45b1-9e49-32d948e91f9e	Druckvorlage	{"time":1555755514534,"blocks":[{"type":"paragraph","data":{"text":"Lass Dir Zeit für die folgenden Fragen und versuche sie so ausführlich und ehrlich zu beantworten wie Du kannst. Niemand anders als nur Du selbst wird das, was Du jetzt schreibst, je lesen.&nbsp;Es soll eine Art Tagebuch sein und Dir später (z.B. nach Deiner Austauschzeit) zeigen, wie Du jetzt gedacht und gefühlt hast. Nachdem Du die Fragen beantwortet hast, stecke den Brief in den Briefumschlag, klebe ihn zu, und schreibe Deinen Namen und Deine Adresse, als Briefanschrift, auf den Umschlag."}},{"type":"list","data":{"style":"ordered","items":["Versuche zu beschreiben, was Dich glücklich machen kann. (Situationen, Personen, Dinge...). Stelle dem gegenüber, was Dich traurig machen und deprimieren kann. (an Dir, anderen Menschen, Situationen ... ).","Versuche den Zustand zu beschreiben, in dem Du Dich im Moment befindest.","Welches sind z.B. Verhaltensweisen und Eigenschaften an Dir, mit denen Du zufrieden bist?&nbsp;","Gibt es demgegenüber Eigenschaften und Verhaltensweisen, die Dich an Dir stören, die Du in Zukunft ändern würdest/möchtest?<br>","Welche sind momentan die erstrebenswertesten Ziele für Dich, Dein Leben, Deine Zukunft? Was hat Dich in den letzten Wochen und Monaten stark beschäftigt. Worüber hast Du viel nachgedacht?<br>","Welche Personen sind im Moment wichtig und besonders wichtig in Deinem Leben? (Bezugspersonen, Freunde etc.)<br>","Wenn man Dich fragen würde ein Buch zu schreiben oder einen Film zu drehen, worüber würde es sein?<br>","Gibt es in dem Zusammenhang irgendetwas, was Du gerne „weitergeben” würdest (z.B. die Konsequenzen aus einer Erfahrung oder ein persönliches Anliegen)?<br>","Wenn Du mit einer Zeitmaschine in Zukunft und Vergangenheit reisen könntest, welche Zeit würdest Du Dir aussuchen und warum?<br>"]}},{"type":"paragraph","data":{"text":"Für einen anderen Blickwinkel:"}},{"type":"paragraph","data":{"text":"Versuche Deinen persönlichen Baum zu zeichnen (zu kreieren, zu kritzeln, etc.) und beschrifte die entsprechenden Teile, um den Baum dann als Ergänzung zu Deinem Brief zu legen."}},{"type":"list","data":{"style":"ordered","items":["<b>Wurzeln</b> („Was gibt mir Halt?“ . . . Familie, Herkunft, Ursprünge . . . )","<b>Stamm</b> („Was macht mich hauptsächlich aus?“ . . . Charakter =&gt; Selbsteinschätzung<br>","<b>Astgabelungen</b> ( . . . Entscheidende Ereignisse, die Dich beeinflusst haben, beeinflussen . .)&nbsp;","<b>Baumkrone</b> (Ziele, Wünsche, Hoffnungen . . . )<br>",". . . freie <b>Phantasie</b>: eventuell Früchte oder merkwürdige Baumbewohner . . ."]}},{"type":"paragraph","data":{"text":"Für Deine Antworten kannst Du die Rückseite und/oder weitere Blätter verwenden. Bitte lege auf jeden Fall diesen Bogen mit Deinen Antworten und Deine Zeichnungen, Cartoons, Zitate, Liebesbrie- fe, Gedichte, Lieder, alles was Dir einfällt, in den Umschlag und klebe ihn zu! Du wirst ihn auf der Nachbereitungstagung zurückerhalten.<br>"}}],"version":"2.12.4"}	2019-04-20 10:19:03.174	\N	00000000-0000-0000-0000-000000000000	\N
\.


--
-- Data for Name: ml_method_level; Type: TABLE DATA; Schema: mdb_data; Owner: methods
--

COPY mdb_data.ml_method_level (ml_id, ml_name, ml_description) FROM stdin;
4fcfd0f3-5be3-46c9-8e3f-0a0b0ad302fa	Abstrakt	\N
fd5bed05-a9cf-451b-b727-1d54526f7baa	Persönlich	\N
d238af87-b465-4c4b-8b9c-db367e458e36	Theoretisch	\N
\.


--
-- Data for Name: mm_method_method_level; Type: TABLE DATA; Schema: mdb_data; Owner: methods
--

COPY mdb_data.mm_method_method_level (mm_method_id, mm_method_level_id) FROM stdin;
8a059932-a32b-43df-a049-1bf982b2c111	fd5bed05-a9cf-451b-b727-1d54526f7baa
c5ad6812-71e9-45b1-9e49-32d948e91f9e	fd5bed05-a9cf-451b-b727-1d54526f7baa
9adacb76-d7b4-41e0-b786-9444e638d5cf	4fcfd0f3-5be3-46c9-8e3f-0a0b0ad302fa
d762a048-c4df-4b03-93da-f88c1048b3e2	fd5bed05-a9cf-451b-b727-1d54526f7baa
\.


--
-- Data for Name: mt_method_type; Type: TABLE DATA; Schema: mdb_data; Owner: methods
--

COPY mdb_data.mt_method_type (mt_id, mt_name, mt_description) FROM stdin;
6595c660-9a8c-4fee-aa10-f01c0d3a7a81	Diskussion	\N
436fafd9-6e39-49df-a5b6-f30ce94eb209	Film	\N
4212ab0f-59b0-427d-8b1a-bbb181de2c27	Simulation	\N
\.


--
-- Data for Name: mm_method_method_type; Type: TABLE DATA; Schema: mdb_data; Owner: methods
--

COPY mdb_data.mm_method_method_type (mm_method_id, mm_method_type_id) FROM stdin;
8a059932-a32b-43df-a049-1bf982b2c111	6595c660-9a8c-4fee-aa10-f01c0d3a7a81
c5ad6812-71e9-45b1-9e49-32d948e91f9e	6595c660-9a8c-4fee-aa10-f01c0d3a7a81
9adacb76-d7b4-41e0-b786-9444e638d5cf	6595c660-9a8c-4fee-aa10-f01c0d3a7a81
d762a048-c4df-4b03-93da-f88c1048b3e2	4212ab0f-59b0-427d-8b1a-bbb181de2c27
\.


--
-- Data for Name: sg_seminar_goal; Type: TABLE DATA; Schema: mdb_data; Owner: methods
--

COPY mdb_data.sg_seminar_goal (sg_method_id, sg_seminar_goal_id) FROM stdin;
8a059932-a32b-43df-a049-1bf982b2c111	9ff4d7ea-89d5-11e8-9a94-a6cf71072f73
c5ad6812-71e9-45b1-9e49-32d948e91f9e	9ff4b6d4-89d5-11e8-9a94-a6cf71072f73
9adacb76-d7b4-41e0-b786-9444e638d5cf	9ff4e2d0-89d5-11e8-9a94-a6cf71072f73
9adacb76-d7b4-41e0-b786-9444e638d5cf	9ff4e08c-89d5-11e8-9a94-a6cf71072f73
9adacb76-d7b4-41e0-b786-9444e638d5cf	9ff4df6a-89d5-11e8-9a94-a6cf71072f73
d762a048-c4df-4b03-93da-f88c1048b3e2	9ff4e2d0-89d5-11e8-9a94-a6cf71072f73
\.


--
-- PostgreSQL database dump complete
--

