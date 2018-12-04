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
(def numbers 
    (for [x (clojure.string/split-lines input)]
        (convert x)))

;; Get the value of all the inputs combined.
(println "The final frequency is " (reduce + 0 numbers))

;; For part B, read each line, determine the current frequency, then check
;; for its presence in a set. If it's there, the duplicate frequency has been
;; found, otherwise add it and carry on.

