(ns cnu.interop
  (:require [goog.object]))

(defn jsobj->edn
  "Convert javascript object to an EDN hash-map."
  ([o] (jsobj->edn o false))
  ([o keywordize-keys?]
   (let [key-fn (if keywordize-keys? keyword identity)]
     (reduce (fn [result key]
               (let [v (.get goog/object o key)]
                 (if (= "function" (goog/typeOf v))
                   result
                   (assoc result (key-fn key) v))))
             {}
             (.getKeys goog/object o)))))
