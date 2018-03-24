(ns cnu.interop-test
  (:require [cljs.test :refer [deftest testing is are]]
            [cnu.interop :refer [jsobj->edn json->edn]]))

(deftest js-object-to-edn
  (testing "jsobj->edn default keywordize keys conversion"
    (are [exp act] (= exp (jsobj->edn act true))
      {:foo "bar"} (clj->js {:foo "bar"})
      {:foo "bar"} (clj->js {"foo" "bar"})
      {:foo "bar"} #js {:foo "bar"}
      {:foo "bar"} #js {"foo" "bar"}
      ))

  (testing "json->edn default keywordize keys conversion"
    (are [exp act] (= exp (json->edn act true))
      {:foo "bar"} (clj->js {:foo "bar"})
      {:foo "bar"} (clj->js {"foo" "bar"})
      {:foo "bar"} #js {:foo "bar"}
      {:foo "bar"} #js {"foo" "bar"}
      ))

  (testing "js->clj keywordize keys conversion - for comparison only"
    (are [exp act] (= exp (js->clj act :keywordize-keys true))
      {:foo "bar"} (clj->js {:foo "bar"})
      {:foo "bar"} (clj->js {"foo" "bar"})
      {:foo "bar"} #js {:foo "bar"}
      {:foo "bar"} #js {"foo" "bar"}
      ))
  )

(deftest json-to-edn
  (testing "json->edn default keywordize keys conversion"
    (are [exp act] (= exp (json->edn act true))
      [{:foo "bar"}] (clj->js [{:foo "bar"}])
      [{:foo "bar"}] (clj->js [{"foo" "bar"}])
      [{:foo "bar"}] #js [{:foo "bar"}]
      ;; [{:foo "bar"}] #js [{"foo" "bar"}]
      ))

  (testing "json->edn (no force keywordizing) conversion"
    (are [exp act] (= exp (json->edn act false))
      ;; [{:foo "bar"}] (clj->js [{:foo "bar"}])
      [{"foo" "bar"}] (clj->js [{"foo" "bar"}])
      [{:foo "bar"}]  #js [{:foo "bar"}]
      [{"foo" "bar"}] #js [{"foo" "bar"}]
      ))

  (testing "js->clj keywordize keys conversion - for comparison only"
    (are [exp act] (= exp (js->clj act :keywordize-keys true))
      [{:foo "bar"}] (clj->js [{:foo "bar"}])
      [{:foo "bar"}] (clj->js [{"foo" "bar"}])
      [{:foo "bar"}] #js [{:foo "bar"}]
      ;; [{:foo "bar"}] #js [{"foo" "bar"}]
      ))

  (testing "js->clj (no force keywordizing) conversion - for comparison only"
    (are [exp act] (= exp (js->clj act))
      ;; [{:foo "bar"}] (clj->js [{:foo "bar"}])
      [{"foo" "bar"}] (clj->js [{"foo" "bar"}])
      [{:foo "bar"}]  #js [{:foo "bar"}]
      [{"foo" "bar"}] #js [{"foo" "bar"}]
      ))
  )

(deftest testing-cljs-core-library-conversions
  (testing "No keywordizing"
    (testing "Roundtrip clj->js"
      (testing "Works as expected"
        (are [e] (= e (-> e clj->js js->clj))
          {"foo" "bar"}
          [{"foo" "bar"}]
          ))
      (testing "Unexpected behavior"
        (are [e] (= e (-> e clj->js js->clj))
          {:foo "bar"}
          [{:foo "bar"}]
          ))
      )
    (testing "Convert #js"
      (testing "Works as expected"
        (are [exp jsliteral] (= exp (js->clj jsliteral))
          {"foo" "bar"}   #js {"foo" "bar"}
          [{"foo" "bar"}] #js [{"foo" "bar"}]
          [{:foo "bar"}]  #js [{:foo "bar"}]
          ))
      (testing "Unexpected behavior"
        (are [exp jsliteral] (= exp (js->clj jsliteral))
          {:foo "bar"} #js {:foo "bar"}
          ))
      ))
  (testing "Forced keywordizing"
    (testing "Roundtrip clj->js"
      (testing "Works as expected"
        (are [exp x] (= exp (-> x clj->js (js->clj :keywordize-keys true)))
          {:foo "bar"}   {"foo" "bar"}
          {:foo "bar"}   {:foo "bar"}
          [{:foo "bar"}] [{"foo" "bar"}]
          [{:foo "bar"}] [{:foo "bar"}]
          ))
      #_(testing "Unexpected behavior"
          (are [exp x] (= exp (-> x clj->js (js->clj :keywordize-keys true)))
            ))
      )
    (testing "Convert #js"
      (testing "Works as expected"
        (are [exp jsliteral] (= exp (js->clj jsliteral :keywordize-keys true))
          {:foo "bar"}   #js {"foo" "bar"}
          {:foo "bar"}   #js {:foo "bar"}
          [{:foo "bar"}] #js [{:foo "bar"}]
          ))
      (testing "Unexpected behavior"
        (are [exp jsliteral] (= exp (js->clj jsliteral :keywordize-keys true))
          [{:foo "bar"}] #js [{"foo" "bar"}]
          ))
      ))
  )

#_(deftest js-object-to-edn
  (testing "string keys clj->js"
    (is (= {"foo" "bar"} (jsobj->edn (clj->js {:foo "bar"}) false)))
    (is (= {"foo" "bar"} (json->edn (clj->js {:foo "bar"}) false)))
    (is (= {"foo" "bar"} (js->clj (clj->js {:foo "bar"}))))
    )
  (testing "string keys #js"
    (is (= {"foo" "bar"} (jsobj->edn #js {:foo "bar"}) false))
    (is (= {"foo" "bar"} (json->edn #js {:foo "bar"}) false))
    (is (= {"foo" "bar"} (js->clj #js {:foo "bar"})))
    )
  (testing "keyword keys"
    (is (= {:foo "bar"} (jsobj->edn (clj->js {:foo "bar"}))))
    (is (= {:foo "bar"} (json->edn (clj->js {:foo "bar"}))))
    (is (= {:foo "bar"} (js->clj (clj->js {:foo "bar"}) :keywordize-keys true)))
    ))

#_(deftest json-to-edn
  (testing "string keys"
    (is (= [{"foo" "bar"}] (json->edn (clj->js [{:foo "bar"}]) false)))
    (is (= [{"foo" "bar"}] (js->clj (clj->js [{:foo "bar"}]) :keywordize-keys false)))
    (is (= [{"foo" "bar"}] (js->clj (clj->js [{:foo "bar"}]))))
    )
  (testing "keyword keys"
    (is (= [{:foo "bar"}] (json->edn (clj->js [{:foo "bar"}]))))
    (is (= [{:foo "bar"}] (js->clj (clj->js [{:foo "bar"}]) :keywordize-keys true)))
    ))
