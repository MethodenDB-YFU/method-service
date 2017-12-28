CREATE TABLE mdb_data.mm_method_method_level (
	mm_method_id			UUID	REFERENCES mdb_data.mm_method(mm_id),
	mm_method_level_id		UUID	REFERENCES mdb_data.ml_method_level(ml_id)
);