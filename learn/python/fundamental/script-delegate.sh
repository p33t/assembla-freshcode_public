#!/usr/bin/env bash
SCRIPT_DIR=`dirname "$0"`
SCRIPT_FILE="$SCRIPT_DIR/command-line.py"
python3 "$SCRIPT_FILE" "$@"