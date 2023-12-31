# Concurrency in Java
Notes on concurrency.

## Official Tutorial
Reference:
[Oracle Concurrency Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html)

### Threads
1. Deadlock, Starvation, Livelock (like hallway pass side-stepping problem)
1. Thread.currentThread().interrupt() to interrupt self without needing 'throws' statement.
1. Object.wait() relinguishes the locked monitor object (which must match the context / sync block) and will only return when lock is reacquired.
1. Object.notifyAll() to tell all threads awaiting lock (in 'waiting' state) to go to 'blocked' state (IE: Something relevant has happened).  Generally only use Object.notify() when dealing with similar threads (in a pool) where only one is needed.

### Sync'n
1. Liveness: Ability of a concurrent app to execute in a timely manner
1. Reentrant Sync'n: Code that has a lock can call code that will acquire the same lock again.
1. Atomic read, write on refs and primitives *except `long`, `double`* (unless using `volatile`)
1. `volatile` is a lighter weight synchronisation mechanism but not as useful and `synchronize`

### Immutability
1. No setters, fields `private final`
1. Prevent method overide with `class final` or contruction via factory method
1. Don't allow mutable objects in fields to change or be accessed externally

### High Level Concurrency Objects
As of Java 5 in `java.util.concurrency` and collections framework.
1. [Lock](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html) family: Can timeout (on a given condition), interrupt-out or immediately abort lock aquisition
1. [Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html): Replacement for direct thread creation using `Runnable`; facilitates thread pools.
1. [ExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html): Run one or many `Callable` (which returns a result), and manage shutdowns (immediate or graceful).
1. ScheduledExecutorService: For scheduled execution (fixed rate, delay)
1. [Executors](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html) class has factory methods to create different types of thread pools.  There are custom thread pool parent classes too.
1. Fork/Join framework (new in Java 7) for utilising all cpu cores to complete work recursively.  'Work stealing' allows idle threads to do work queued up for other threads.
   * [ForkJoinPool](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html) is an ExecutorService
   * Used by Arrays.parrallelSort() (Java 8)
   * Used by `java.util.streams` (Java 8)
1. Concurrent collections for thread orchestrating work queues, atomic map ops, approximate-match map ops
1. Atomic variables that act like a `volatile` field with richer operations (EG: AtomicInteger.incrementAndGet())
1. [ThreadLocalRandom](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadLocalRandom.html) allows concurrent random number generation with less contention (Unlike Math.random())

## Inter-process Communication (IPC)
See [ProcessBuilder](https://docs.oracle.com/javase/9/docs/api/index.html?java/lang/ProcessBuilder.html) facility in Java
### Memory Mapped File
Work with file contents as if it were memory
1. OS takes care of IO using same technology as Virtual Memory / Paging
1. Much better performance because OS is optimising
1. Less conttrol over order that contents are saved
1. Tricky if mutable data

"MappedBus" project on GitHub
1. High throughput
1. Low latency message passing

### Sockets (across network or same computer)
Need socket protocol like:
1. SOAP (less coupling of participants)
1. Serialised object stream
1. Custom protocol for optimal performance

I think this includes RMI, Message Passing, REST

### Temporary Files

### Unix Style Pipes
Via 'stdin' & 'stdout'.

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
1. Better to test continuously and detect performance regressions as soon as they enter the code.
   * Time spent on continous performance fixing is far less than fixing just prior to release.
   * Results in higher software quality and near-trivial fixing phase after performance testing.  
Which leads to greater planning confidence by engineering teams.

1. Dynamic architecture validation to check collected statistics during performance tests.
   * EG: Can examine number of database calls or the frequency of each type of call.  
   * Some problems are easier to detect than others.  Need savvy architect. 

## RESTful Web Services
From [JEE Tutorial](https://docs.oracle.com/javaee/7/tutorial/jaxrs001.htm#GIJQY)

Uses the stateless protocol such as HTTP.
Resources are represented as documents with simple, well-defined operations.

### Principles
1. URI for global address space for resource and service discovery
1. PUT, GET, POST, DELETE for CRUD operations
1. Resources decoupled from representation so various formats can be used.
Metadata about resources for, say, caching, parity, format neg'n, authen'n, author'n.
1. Stateful interaction achieved by embedding state in responses.

SOAP (Simple object access protocol) is more sophisticated and transmits in only one format: XML.
EG: Communications retry, identity verification, better ACID compliant transactions.
But REST is simpler and easier for browser based clients.  Depending on needs of application, 
SOAP can be easier to use that REST.

### Performance
#### Client Side
Static Content
1. Cookie-free domain from a CDN
1. Can make full use of HTTP headers for caching (expires, last-modified, ETag)
1. Compression using standard gzip or specific compression
1. Reduce files being transferred with concatenated javascript files and CSS sprites 

Dynamic Content
1. Compression still an option
1. Can do limited caching for slow-changing content

#### Server Side
Can cache database records (EJB JPA directive @Cacheable).

#### Specific Tips
By [Dexter Legaspi](https://gist.github.com/dalegaspi/d02eee4ccead31057276)
1. Jersey REST is recommended framework.  Play is apparently bloated, over-engineered
1. Don't spawn threads in request handler.
1. Be mindful of logging (and don't get fancy.  [Stack trace ops](http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method) are expensive)
1. Simple non-distributed caching: Google Guava
1. Pooled objects: Apache Commons Pool
1. Don't use Spring (its slow)
1. Tomcat easier to set up than Jetty.  Don't worry about others.
1. Skip the non-blocking I/O hype (for now).  Java is inherently 'blocking'
1. Likely don't need `synchronized` keyword (you're prob'ly doing it wrong)
1. `SecureRandom` has a synchronized method so lots of contention (See `ThreadLocalRandom`? - pwl)