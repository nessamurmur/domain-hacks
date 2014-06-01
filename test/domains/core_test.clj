(ns domains.core-test
  (:require [clojure.test :refer :all]
            [domains.core :refer :all]))

(deftest test-replace-last
  (testing "only replaces last occurence"
    (is (= "hi there you" (replace-last "hi there there" "there" "you")))))

(deftest test-make-domain
  (testing "transforms a string to a domain name"
    (is (= "ti.me" (make-domain "time" "me")))))

(deftest test-tld-matches
  (testing "finds words that end with a given tld"
    (binding [words '("time" "mime" "soup" "salad")]
      (is (= '("time" "mime") (tld-matches "me"))))))

(deftest test-domains-for
  (testing "finds domains for a given tld"
    (binding [words '("time" "mime" "soup" "salad")]
      (is (= '("ti.me" "mi.me") (domains-for "me"))))))
