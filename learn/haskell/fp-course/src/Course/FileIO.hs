{-# LANGUAGE NoImplicitPrelude #-}
{-# LANGUAGE ScopedTypeVariables #-}
{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE RebindableSyntax #-}

module Course.FileIO where

import Course.Core
import Course.Applicative
import Course.Monad
import Course.Functor
import Course.List

{-

Useful Functions --

  getArgs :: IO (List Chars)
  putStrLn :: Chars -> IO ()
  readFile :: Chars -> IO Chars
  lines :: Chars -> List Chars
  void :: IO a -> IO ()

Abstractions --
  Applicative, Monad:

    <$>, <*>, >>=, =<<, pure

Problem --
  Given a single argument of a file name, read that file,
  each line of that file contains the name of another file,
  read the referenced file and print out its name and contents.

Example --
Given file files.txt, containing:
  a.txt
  b.txt
  c.txt

And a.txt, containing:
  the contents of a

And b.txt, containing:
  the contents of b

And c.txt, containing:
  the contents of c

To test this module, load ghci in the root of the project directory, and do
    >> :main "share/files.txt"

Example output:

$ ghci
GHCi, version ... 
Loading package...
Loading ...
[ 1 of 28] Compiling (etc...
...
Ok, modules loaded: Course, etc...
>> :main "share/files.txt"
============ share/a.txt
the contents of a

============ share/b.txt
the contents of b

============ share/c.txt
the contents of c

-}

-- /Tip:/ use @getArgs@ and @run@
main ::
  IO ()
main =
  do a <- getArgs
     headOr (putStrLn "Need at least 1 arg") (run <$> a)
--  error "todo: Course.FileIO#main"

type FilePath =
  Chars

-- /Tip:/ Use @getFiles@ and @printFiles@.
run ::
  FilePath
  -> IO ()
run fp =
  do c <- readFile fp
     x <- getFiles (lines c)
     printFiles x
--  error "todo: Course.FileIO#run"

getFiles ::
  List FilePath
  -> IO (List (FilePath, Chars))
getFiles = sequence . ((<$>) getFile)
-- 1st answer... getFiles fps = sequence ((<$>) getFile fps)
--  error "todo: Course.FileIO#getFiles"

getFile ::
  FilePath
  -> IO (FilePath, Chars)
getFile = lift2 (<$>) (,) readFile
-- my answer... getFile fp = (<$>) (\cs -> (fp, cs)) (readFile fp)
--  error "todo: Course.FileIO#getFile"

printFiles ::
  List (FilePath, Chars)
  -> IO ()
printFiles =
  traverse_ (uncurry printFile)
--  void . sequence . (<$>) (uncurry printFile)
--printFiles t2s =
--  let r = (\(fp, cs) -> printFile fp cs)
--  in void (sequence )

-- my attempt.... traverse_ (printFile ((<$>) (\(fp, cs) -> printFile fp cs) t2s) )
--  error "todo: Course.FileIO#printFiles"

printFile ::
  FilePath
  -> Chars
  -> IO ()
printFile fp cs =
  let list = ("======" ++ fp) :. cs :. Nil
--  in void (sequence (putStrLn <$> list))
  in traverse_ putStrLn list

--printFile fp cs = void (sequence ((<$>) putStrLn (("========" ++ fp) :. cs :. Nil)))
  --error "todo: Course.FileIO#printFile"

traverse_ ::
  Applicative f =>
  (a -> f b)
  -> List a
  -> f ()

traverse_ f x =
  void (sequence (f <$> x))
