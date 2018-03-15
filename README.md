# cljs-node-utils

[![Clojars Project](https://img.shields.io/clojars/v/org.jasani/cnu.svg)](https://clojars.org/org.jasani/cnu)

A Clojure library of utilities for working with Node.js

Note: this is an early release and the api may change drastically.

## Usage

```clojure
(:require [cnu.interop :refer [jsobj->edn])

;; wherever we need to convert a javascript object into edn
(jsobj->edn (clj->js {:foo "bar"}))
;;=> {:foo "bar"}

(jsobj->edn (clj->js {:foo "bar"}) false)
;;=> {"foo" "bar"}

;; or arbitrary json to edn
(json->edn (clj->js {:foo "bar"}))
;;=> {:foo "bar"}

(json->edn (clj->js {:foo "bar"}) false)
;;=> {"foo" "bar"}
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
