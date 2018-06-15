CREATE SCHEMA mdb_data;

CREATE TYPE mdb_data.mr_role AS enum (
  'ADMIN',
  'EDITOR',
  'WRITER',
  'READER'
);

CREATE TABLE mdb_data.ml_method_level (
  ml_id 					      UUID		            PRIMARY KEY,
  ml_name					      TEXT		            NOT NULL,
  ml_description	      TEXT
);

CREATE TABLE mdb_data.mt_method_type (
  mt_id						      UUID		            PRIMARY KEY,
  mt_name					      TEXT		            NOT NULL,
  mt_description	      TEXT
);

CREATE TABLE mdb_data.mu_user (
  mu_id			            UUID							  PRIMARY KEY,
  mu_role		            mdb_data.mr_role
);

CREATE TABLE mdb_data.mm_method (
  mm_id									UUID				        PRIMARY KEY,
  mm_title							TEXT				        NOT NULL,
  mm_content						TEXT,
  mm_seminar_type_id		UUID,
  mm_created_at					TIMESTAMP 	        DEFAULT now(),
  mm_modified_at				TIMESTAMP 	        DEFAULT now(),
  mm_modified_by				UUID				        REFERENCES mdb_data.mu_user(mu_id),
  mm_created_by					UUID				        REFERENCES mdb_data.mu_user(mu_id)
);

CREATE TABLE mdb_data.sg_seminar_goal (
  sg_method_id				  UUID		            REFERENCES mdb_data.mm_method(mm_id),
  sg_seminar_goal_id	  UUID
);

CREATE TABLE mdb_data.ma_attachment (
  ma_id						      UUID			          PRIMARY KEY,
  ma_method_id		      UUID			          REFERENCES mdb_data.mm_method(mm_id),
  ma_title				      TEXT			          NOT NULL,
  ma_content			      TEXT			          NOT NULL,
  ma_created_at		      TIMESTAMP	          DEFAULT now(),
  ma_modified_at	      TIMESTAMP	          DEFAULT now(),
  ma_created_by		      UUID			          REFERENCES mdb_data.mu_user(mu_id),
  ma_modified_by	      UUID			          REFERENCES mdb_data.mu_user(mu_id)
);

CREATE TABLE mdb_data.mm_method_method_level (
  mm_method_id			    UUID	              REFERENCES mdb_data.mm_method(mm_id),
  mm_method_level_id		UUID	              REFERENCES mdb_data.ml_method_level(ml_id)
);

CREATE TABLE mdb_data.mm_method_method_type (
  mm_method_id			    UUID	              REFERENCES mdb_data.mm_method(mm_id),
  mm_method_type_id		  UUID	              REFERENCES mdb_data.mt_method_type(mt_id)
);