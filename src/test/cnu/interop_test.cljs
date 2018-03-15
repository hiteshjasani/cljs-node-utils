(ns cnu.interop-test
  (:require [cljs.test :refer [deftest testing is]]
            [cnu.interop :refer [jsobj->edn json->edn]]))

(deftest js-object-to-edn
  (testing "string keys"
    (is (= {"foo" "bar"} (jsobj->edn (clj->js {:foo "bar"}) false)))
    (is (= {"foo" "bar"} (json->edn (clj->js {:foo "bar"}) false)))
    (is (= {"foo" "bar"} (js->clj (clj->js {:foo "bar"}))))
    )
  (testing "keyword keys"
    (is (= {:foo "bar"} (jsobj->edn (clj->js {:foo "bar"}))))
    (is (= {:foo "bar"} (json->edn (clj->js {:foo "bar"}))))
    (is (= {:foo "bar"} (js->clj (clj->js {:foo "bar"}) :keywordize-keys true)))
    ))

(deftest json-to-edn
  (testing "string keys"
    (is (= [{"foo" "bar"}] (json->edn (clj->js [{:foo "bar"}]) false)))
    (is (= [{"foo" "bar"}] (js->clj (clj->js [{:foo "bar"}]) :keywordize-keys false)))
    (is (= [{"foo" "bar"}] (js->clj (clj->js [{:foo "bar"}]))))
    )
  (testing "keyword keys"
    (is (= [{:foo "bar"}] (json->edn (clj->js [{:foo "bar"}]))))
    (is (= [{:foo "bar"}] (js->clj (clj->js [{:foo "bar"}]) :keywordize-keys true)))
    ))
