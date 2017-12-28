CREATE TABLE mdb_data.ma_attachment (
	ma_id				UUID		PRIMARY KEY,
	ma_method_id		UUID		REFERENCES mdb_data.mm_method(mm_id),
	ma_content			TEXT		NOT NULL,
	ma_created_at		TIMESTAMP	DEFAULT now(),
	ma_modified_at		TIMESTAMP	DEFAULT now(),
	ma_created_by		UUID		REFERENCES mdb_data.mu_user(mu_id),
	ma_modified_by		UUID		REFERENCES mdb_data.mu_user(mu_id)
);