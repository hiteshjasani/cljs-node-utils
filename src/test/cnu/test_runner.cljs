(ns cnu.test-runner
  (:require [cljs.test :refer [run-tests]]
            [cnu.interop-test]))

(enable-console-print!)

(run-tests
 'cnu.interop-test)
