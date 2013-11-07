This folder holds specialised code for a specific domain.
The domain might add extra fields to entities or even entire entities.
There might also be certain UI's that are not present in the 'vanilla' code base.

Alternatively one might say that 'vanilla' is a myth and that it is really just a 'common' that has reusable chunks.
In any case the long-term app will have a mixture of configurable 'meta' style parts and specialized domain specific parts.
Different sub package names might be a better place to start:
- xxx.core
- xxx.util
- xxx.special
