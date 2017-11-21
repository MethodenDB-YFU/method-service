CREATE TABLE method (
	id					SERIAL,
	title				TEXT		NOT NULL,
	content				TEXT,
	seminar_type_id		INT			NOT NULL,
	created_at			TIMESTAMP 	DEFAULT now(),
	modified_at			TIMESTAMP 	DEFAULT now(),
	created_by			INT
);