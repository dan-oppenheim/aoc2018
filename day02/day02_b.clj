; determine if there is one item difference between equal length sequences
; todo: return to this to convert to a recursive function
(defn single-diff [a b]
    (loop [i 0  ;; character index
           c 0] ;; number of differences
        (let [ai (get a i) ; character in a at index i
              bi (get b i) ; character in b at index i
              len (count a)
              c2 (if (not= ai bi) (inc c) c)] ; increment c if they're not equal
            (cond 
              (> c2 1) false ; can only have a single difference
              (= i len) (= c2 1) ; if we've got to the end, return whether had a single difference
              :else (recur (inc i) c2))))) ; otherwise loop to next character

; Checks id against every item in others to see if any combination has only
; a single difference. Returns the pair that match or false if none do.
(defn check-id [id others]
    (cond
        (empty? others) false
        (single-diff id (first others)) [id (first others)]
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
; todo: There must be a nicer way of doing this?
(defn to-common [a b]
    (map (fn [a b] (if (= a b) a)) a b))

(defn get-code [ids]
    (let [common (find-boxes (first ids) (rest ids))]
        (if (not common)
            "not found"
            (apply str (to-common (get common 0) (get common 1))))))

(println
    "The code is: "
    (get-code (sort (clojure.string/split-lines (slurp "input.txt")))))

