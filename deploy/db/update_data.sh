python3 ../lib/crawler/new/crawler.py

rm db/20_seed/20_seed.sql

mv ../lib/crawler/new/insert.sql db/20_seed/20_seed.sql

deploy/db/build.sh {{VERSION}}

deploy/db/up.sh

rm db/20_seed/20_seed.sql

pg_dump -h 0.0.0.0 -U spring --data-only shop > db/20_seed/20_seed.sql
