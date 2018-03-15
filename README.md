# cljs-node-utils

A Clojure library of utilities for working with Node.js

## Usage

```clojure
(:require [cnu.interop :refer [jsobj->edn])

;; wherever we need to convert a javascript object into edn
(jsobj->edn #js {:foo "bar"})
;;=> {"foo" "bar"}

(jsobj->edn #js {:foo "bar"} true)
;;=> {:foo "bar"}
```

### In lumo

```shell
% lumo -D org.jasani/cnu:0.1.0 myscript.cljs
```

Note that lumo doesn't download clojars dependencies.  They must
already be in your local maven repo to use them.

## License

Copyright © 2018 Hitesh Jasani

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
