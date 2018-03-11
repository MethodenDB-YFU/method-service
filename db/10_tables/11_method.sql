CREATE TABLE mdb_data.mm_method (
	mm_id									UUID				PRIMARY KEY,
	mm_title							TEXT				NOT NULL,
	mm_content						TEXT,
	mm_seminar_type_id		UUID				REFERENCES mdb_data.st_seminar_type(st_id),
	mm_created_at					TIMESTAMP 	DEFAULT now(),
	mm_modified_at				TIMESTAMP 	DEFAULT now(),
	mm_modified_by				UUID				REFERENCES mdb_data.mu_user(mu_id),
	mm_created_by					UUID				REFERENCES mdb_data.mu_user(mu_id)
);