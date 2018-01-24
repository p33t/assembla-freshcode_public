Notes on concurrency.

Reference:
[Oracle Concurrency Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html)

1. Deadlock, Starvation, Livelock (like hallway pass side-stepping problem)
1. Thread.currentThread().interrupt() to interrupt self without needing 'throws' statement.
1. Object.wait() relinguishes the locked monitor object (which must match the context / sync block) and will only return when lock is reacquired.
1. Object.notifyAll() to tell all threads awaiting lock (in 'waiting' state) to go to 'blocked' state (IE: Something relevant has happened).  Generally only use Object.notify() when dealing with similar threads (in a pool) where only one is needed.

## Memory Leaks
From [Dynatrace eBook](https://www.dynatrace.com/resources/ebooks/javabook/memory-leaks/).

Fast and slow (difficult to detect) leaks from unreleased objects.
1. Memory usage trends for easy to find leaks.
1. Memory hotspot view can reveal enormous objects.

### Causes
1. Mutable static fields, collections
1. Thread-local vars (Examine the ThreadLocalMap of a heap dump)
1. Circular or complex references (Examine heap dump to find the 'innocuous' reference)
1. JNI object references (from native code) (Use heap dump tool that marks native refs)

What about blocked / waiting threads?

More from [Stackify](https://stackify.com/memory-leaks-java/):
1. String intern (gets placed in PermGen).  For <= Java 7.  Java 8 not so much.
1. Unclosed streams / connections
1. HashSet with objects without equals / hashCode implementations.  Similar objects never ignored.

### Tips
1. Verbose garbage collection logging (`-verbose:gc` JVM param)
1. Profiling.  Visual VM for entry level.  YourKit for next level.
1. Code reviewing.


## High Performance
Better to test continuously and detect performance regressions as soon as they enter the code.
Time spent on continous performance fixing is far less than fixing just prior to release.

Results in higher software quality and near-trivial fixing phase after performance testing.  
Which leads to greater planning confidence by engineering teams.

Dynamic architecture validation to check collected statistics during performance tests.
Can examine number of database calls or the frequency of each type of call.  
Some problems are easier to detect than others. 

