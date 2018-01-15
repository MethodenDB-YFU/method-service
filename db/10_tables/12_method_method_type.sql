CREATE TABLE mdb_data.mm_method_method_type (
	mm_method_id			UUID	REFERENCES mdb_data.mm_method(mm_id),
	mm_method_type_id		UUID	REFERENCES mdb_data.mt_method_type(mt_id)
);