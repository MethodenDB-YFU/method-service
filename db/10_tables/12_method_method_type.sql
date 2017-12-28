CREATE TABLE mdb_data.mm_method_method_type (
	method_id			UUID	REFERENCES mdb_data.mm_method(mm_id),
	method_type_id		UUID	REFERENCES mdb_data.mt_method_type(mt_id)
);