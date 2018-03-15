(ns cnu.interop
  (:require [goog.object]))

(defn json->edn
  "Convert json to EDN.  Default is to keywordize keys."
  ([e] (json->edn e true))
  ([e keywordize-keys?]
   (js->clj e :keywordize-keys keywordize-keys?)))

(defn jsobj->edn
  "Convert javascript object to an EDN hash-map. Default is to keywordize keys."
  ([o] (jsobj->edn o true))
  ([o keywordize-keys?]
   (let [key-fn (if keywordize-keys? keyword identity)]
     (reduce (fn [result key]
               (let [v (.get goog/object o key)]
                 (if (= "function" (goog/typeOf v))
                   result
                   (assoc result (key-fn key) v))))
             {}
             (.getKeys goog/object o)))))
