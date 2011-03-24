#!/bin/bash

SCRIPT_DIR=`dirname $0`

run ()
{
  if [ -a $SCRIPT_DIR/classes ]; then 
    rm -rf $SCRIPT_DIR/classes
  fi
  mkdir $SCRIPT_DIR/classes

  scalac -d $SCRIPT_DIR/classes $SCRIPT_DIR/src/HelloWorld.scala $SCRIPT_DIR/src/pkg/HelloWorld2.scala
  if [ $? -ne 0 ]; then echo 'scalac failed'; return 1; fi

  scala -cp classes HelloWorld
  scala -cp classes pkg.HelloWorld2
}

run
