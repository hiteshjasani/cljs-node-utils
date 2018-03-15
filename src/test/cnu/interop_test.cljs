(ns cnu.interop-test
  (:require [cljs.test :refer [deftest testing is]]
            [cnu.interop :refer [jsobj->edn]]))

(deftest js-object-to-edn
  (testing "string keys"
    (is (= {"foo" "bar"} (jsobj->edn #js {:foo "bar"})))
    (is (= {"foo" "bar"} (js->clj #js {:foo "bar"})))
    )
  (testing "keyword keys"
    (is (= {:foo "bar"} (jsobj->edn #js {:foo "bar"} true)))
    (is (= {:foo "bar"} (js->clj #js {:foo "bar"} :keywordize-keys true)))
    ))
