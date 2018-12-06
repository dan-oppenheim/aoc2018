(def ids (sort (clojure.string/split-lines (slurp "input.txt"))))

; determine if there is one item difference between equal length sequences
(defn single-diff [a b]
    (loop [i 0  ;; character index
           c 0] ;; number of differences
        (let [ai (get a i)
              bi (get b i)
              len (count a)
              c2 (if (not= ai bi) (inc c) c)]
            (cond 
              (> c2 1) false
              (= i len) (= c2 1)
              :else (recur (inc i) c2)))))


;(println (single-diff "foo" "fop")) ; true
;(println (single-diff "foo" "fpp")) ; false
;(println (single-diff "foo" "foo")) ; false
