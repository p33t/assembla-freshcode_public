#!/usr/bin/env bash
SCRIPT_DIR=`dirname "$0"`
SCRIPT_FILE="$SCRIPT_DIR/command-line.py"
python3 "$SCRIPT_FILE" blah
MY_CODE=$?
echo "Exit code ${MY_CODE}"


python3 "$SCRIPT_FILE" -h
MY_CODE=$?
echo "Exit code ${MY_CODE}"


python3 "$SCRIPT_FILE" -r reqd_val
MY_CODE=$?
echo "Exit code ${MY_CODE}"


python3 "$SCRIPT_FILE" -r reqd_val -f --opt alt-opt my_single one two "buckle my shoe"
MY_CODE=$?
echo "Exit code ${MY_CODE}"

