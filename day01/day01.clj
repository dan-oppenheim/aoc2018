;; Advent of Code 2018 - Day 1

;; Transforms the string 's' into an integer, respecting a leading sign.
(defn convert [s]
    (let [num (Integer/parseInt (subs s 1))]
    (if (= (get s 0) \-)
        (- num)
        num)))

;; Reads the input into a single string. This could probably be something
;; that reads the input as a sequence of lines.
(def input (slurp "input.txt"))

;; Splits the input and converts each line into an integer, storing the values
;; in a vector.
(def changes 
    (for [x (clojure.string/split-lines input)]
        (convert x)))

;; Get the value of all the inputs combined.
(println "The final frequency is" (reduce + 0 changes))


;; Adds each frequency to a running total until that total is seen twice and 
;; returns that total.
(defn find-freq-repeat [changes]
    (loop 
        [input (cycle changes) ;; generate an infinite sequence
         total 0
         seen #{}]
    
        (if (contains? seen total)
            total
            (let [newtotal (+ total (first input))]
            (recur ;; restarts the loop, using the values before its bindings
                (rest input) ;; all but the first
                newtotal
                (conj seen total)))))) ;; append unseen total to seen set

(println "First match is" (find-freq-repeat changes))