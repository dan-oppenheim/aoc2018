; determine if there is one item difference between equal length sequences
(defn single-diff [a b count]
    (if (empty? a) (= count 1)
        (let [aa (first a)
              bb (first b)
              c2 (if (not= aa bb) (inc count) count)]
            (cond
                (> c2 1) false ; can only have a single difference
                :else (recur (rest a) (rest b) c2))))) ; recurse to rest of ids


; Checks id against every item in others to see if any combination has only
; a single difference. Returns the pair that match or false if none do.
(defn check-id [id others]
    (cond
        (empty? others) false
        (single-diff id (first others) 0) [id (first others)]
        :else (recur id (rest others))))

; Recursive function that checks every box against every other to compare
; ids, returning the first that matches the criteria.
(defn find-boxes [box others]
    (let [found (check-id box others)]
        (cond
            (empty? others) false
            (coll? found) found
            :else (recur (first others) (rest others)))))

; Returns a new list that is all of the matching characters in a and b
(defn to-common [a b]
    (map (fn [a b] (if (= a b) a)) a b))

; Get the code by searching for the two ids that differ by a single letter
; and return a string containing all of the matching letters or "not found" on
; failure.
(defn get-code [ids]
    (let [common (find-boxes (first ids) (rest ids))]
        (if (not common)
            "not found"
            (apply str (to-common (get common 0) (get common 1))))))

(println
    "The code is:"
    (get-code (sort (clojure.string/split-lines (slurp "input.txt")))))

