(ns domains.core
  (:use [clojure.string :as string :only (split-lines)]))

(def words (string/split-lines
            (slurp "./resources/words.txt")))

(def tlds (string/split-lines
           (slurp "./resources/tlds.txt")))

(defn find-domains [word tlds]
  (filter #(.endsWith word %)
          (map #(.toLowerCase %) tlds)))
