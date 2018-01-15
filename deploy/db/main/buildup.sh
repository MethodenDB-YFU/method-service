#!/bin/bash

sh ./deploy/db/main/build.sh $1
sh ./deploy/db/main/up.sh
