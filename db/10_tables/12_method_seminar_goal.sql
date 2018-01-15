CREATE TABLE mdb_data.mm_method_seminar_goal (
	mm_method_id			UUID		REFERENCES mdb_data.mm_method(mm_id),
	mm_seminar_goal_id		UUID		REFERENCES mdb_data.sg_seminar_goal(sg_id)
);