CREATE TABLE mdb_data.st_seminar_type (
	st_id 				UUID			PRIMARY KEY,
	st_foreign_id 		INT,
	st_name 			TEXT        	NOT NULL
);