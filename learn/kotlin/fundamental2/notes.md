Extra notes on Kotlin fundamentals
==================================
1. 'internal' visibility for use within a 'module'
1. out vs in type args:
  * Declaration-site variance: out X = covariant, in X = contravariant;  corresponding to producer, consumer
  * Useful for interfaces whose types are entirely produced or entirely consumed
  * Java has use-site variance with ? extends X and ? super X (not as convenient)
  * Array is invariant  (unlike Java)
  * TODO projections