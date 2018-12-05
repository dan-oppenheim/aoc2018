;; Advent of Code 2018 - Day 2

; Returns true if item's val is a two or three
(defn two-or-three [item]
    (let [v (val item)]
        (or (= v 2)
            (= v 3))))

; Reads an id string, counts the frequencies of the characters, then returns
; a set. If the set is empty, none of the characters are repeated two or three
; times. If one or more of the characters has frequencies of two or three, then
; the set will contain 2 or 3 or both.
(defn process-id [id]
    (let [freq (frequencies id)]
        (set (vals (filter two-or-three freq)))))

; Get a new sequence of sets for each of the ids. 
(defn get-frequencies [ids]
    (map (fn [id] (process-id id)) ids))

(defn inc-frequencies [acc f]
    {:two (+ (get acc :two) (if (contains? f 2 ) 1 0))
     :three (+ (get acc :three) (if (contains? f 3) 1 0))})

; Sums all the instances of ids with two and three character frequencies.
(defn count-two-and-three [freqs]
    (reduce inc-frequencies {:two 0 :three 0} freqs))

(defn calc-checksum [ids]
    (let [counts (count-two-and-three (get-frequencies ids))]
        (println (* (get counts :two)
                    (get counts :three)))))

(calc-checksum (clojure.string/split-lines (slurp "input.txt")))
