(defproject org.jasani/cnu "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0" :scope "provided"]
                 [org.clojure/clojurescript "1.10.126" :scope "provided"]
                 ]
  :plugins [[lein-cljsbuild "1.1.7"]]
;; Not needed since the bug fix with clojurescript transpiling with java 9
;;  :jvm-opts ["--add-modules" "java.xml.bind"]
  :source-paths ["src/cljs"]
  :cljsbuild
  {:builds
   [{:id           "test-node"
     :source-paths ["src/cljs" "src/test"]
     :compiler     {:main          cnu.test-runner
                    :output-to     "target/test-node.js"
                    :output-dir    "target/out-test-node"
                    :pretty-print  true
                    :target        :nodejs
                    :optimizations :none}}
    {:id           "test-simple"
     :source-paths ["src/cljs" "src/test"]
     :compiler     {:main          cnu.test-runner
                    :output-to     "target/test-simple.js"
                    :output-dir    "target/out-test-simple"
                    :target        :nodejs
                    :optimizations :simple}}
    {:id           "test-adv"
     :source-paths ["src/cljs" "src/test"]
     :compiler     {:main          cnu.test-runner
                    :output-to     "target/test-adv.js"
                    :output-dir    "target/out-test-adv"
                    :target        :nodejs
                    :optimizations :advanced}}
    {:id           "simple"
     :source-paths ["src/cljs"]
     :compiler     {
                    :output-to     "target/simple.js"
                    :output-dir    "target/out-simple"
                    :optimizations :simple}}
    {:id           "simple-node"
     :source-paths ["src/cljs"]
     :compiler     {
                    :output-to     "target/simple-node.js"
                    :output-dir    "target/out-simple-node"
                    :target        :nodejs
                    :optimizations :simple}}
    {:id           "adv"
     :source-paths ["src/cljs"]
     :compiler     {
                    :output-to     "target/adv.js"
                    :output-dir    "target/out-adv"
                    :optimizations :advanced}}
    {:id           "adv-node"
     :source-paths ["src/cljs"]
     :compiler     {
                    :output-to     "target/adv-node.js"
                    :output-dir    "target/out-adv-node"
                    :target        :nodejs
                    :optimizations :advanced}}
    ]}
  )
