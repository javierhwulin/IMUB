#!/usr/bin/env sh
set -ue

DIR=$(dirname $(realpath ${0}))
DB_FILE_NAME="imub.sqlite3"

# Delete db file if exists
test -e ${DIR}/${DB_FILE_NAME} && rm -f "${DIR}/${DB_FILE_NAME}"

# Check sqlite3 binary exists
if ! which sqlite3 > /dev/null 2>&1; then
    echo "sqlite3 binary not found"
    exit 1
fi

# Restore db schema and data
set -v
$(which sqlite3) -echo "${DIR}/${DB_FILE_NAME}" < "${DIR}/_schema.sql";
$(which sqlite3) -echo "${DIR}/${DB_FILE_NAME}" < "${DIR}/_data.sql";
set +v
