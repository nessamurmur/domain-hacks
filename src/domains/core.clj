(ns domains.core
  (:require [clojure.string :as string :only (split-lines
                                              replace)]))

(def words (string/split-lines
            (slurp "./resources/words.txt")))

(def tlds (string/split-lines
           (slurp "./resources/tlds.txt")))

(defn replace-last [word pattern replacement]
  (str (.substring word 0 (- (count word)
                             (count pattern)))
       replacement))

(defn tld-matches [tld]
  (flatten
   (filter #(not (or (empty? %) (.startsWith % tld)))
           (filter #(.endsWith % tld) words))))

(defn make-domain [word tld]
  (replace-last word tld (str "." tld)))

(defn domains-for [tld]
  (let [matches (tld-matches tld)]
    (map #(make-domain % tld) matches)))

(defn possible-domains []
  (flatten (map domains-for tlds)))
