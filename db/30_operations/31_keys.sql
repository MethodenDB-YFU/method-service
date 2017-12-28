-- ALTER TABLE ONLY method_level			ADD CONSTRAINT method_level_pkey			PRIMARY KEY (id);
-- ALTER TABLE ONLY method_type			ADD CONSTRAINT method_type_pkey				PRIMARY KEY (id);
-- ALTER TABLE ONLY seminar_type			ADD CONSTRAINT seminar_type_pkey			PRIMARY KEY (id);
-- ALTER TABLE ONLY mdb_user				ADD CONSTRAINT user_pkey					PRIMARY KEY (id);
-- ALTER TABLE ONLY method 				ADD CONSTRAINT method_pkey 					PRIMARY KEY (id);
-- ALTER TABLE ONLY attachment				ADD CONSTRAINT attachment_pkey				PRIMARY KEY (id);

-- ALTER TABLE ONLY attachment 			ADD CONSTRAINT attachment_method_fkey					FOREIGN KEY (method_id)			REFERENCES method(id);
-- ALTER TABLE ONLY attachment 			ADD CONSTRAINT attachment_modified_by_fkey				FOREIGN KEY (modified_by)		REFERENCES mdb_user(id);
-- ALTER TABLE ONLY attachment 			ADD CONSTRAINT attachment_created_by_fkey				FOREIGN KEY (created_by)		REFERENCES mdb_user(id);
-- ALTER TABLE ONLY method 				ADD CONSTRAINT method_seminar_type_fkey					FOREIGN KEY (seminar_type_id)	REFERENCES seminar_type(id);	
-- ALTER TABLE ONLY method 				ADD CONSTRAINT method_modified_by_fkey					FOREIGN KEY (modified_by)		REFERENCES mdb_user(id);
-- ALTER TABLE ONLY method 				ADD CONSTRAINT method_created_by_fkey					FOREIGN KEY (created_by)		REFERENCES mdb_user(id);
-- ALTER TABLE ONLY method_method_level 	ADD CONSTRAINT method_method_level_method_fkey			FOREIGN KEY (method_id)			REFERENCES method(id);
-- ALTER TABLE ONLY method_method_level	ADD CONSTRAINT method_method_level_method_level_fkey	FOREIGN KEY (method_level_id)	REFERENCES method_level(id);
-- ALTER TABLE ONLY method_method_type 	ADD CONSTRAINT method_method_type_method_fkey			FOREIGN KEY	(method_id)			REFERENCES method(id);
-- ALTER TABLE ONLY method_method_type 	ADD CONSTRAINT method_method_type_method_type_fkey		FOREIGN KEY (method_type_id)	REFERENCES method_type (id);

