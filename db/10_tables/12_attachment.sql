CREATE TABLE attachment (
	id			SERIAL,
	method_id	INT,
	content		TEXT		NOT NULL,
	created_at	TIMESTAMP	DEFAULT now(),
	modified_at	TIMESTAMP	DEFAULT now(),
	created_by	INT,
	modified_by	INT
);