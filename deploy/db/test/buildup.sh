#!/bin/bash

sh ./deploy/db/test/build.sh $1
sh ./deploy/db/test/up.sh
