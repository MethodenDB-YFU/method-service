CREATE TABLE mdb_data.sg_seminar_goal (
	sg_id				UUID		PRIMARY KEY,
	sg_name				TEXT		NOT NULL,
	sg_description		TEXT,
	sg_seminar_type_id	UUID		REFERENCES mdb_data.st_seminar_type(st_id)
);