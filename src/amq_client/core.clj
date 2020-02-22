(ns amq-client.core
  (:gen-class)
  (:import (java.nio ByteBuffer)))

(def wireformat-info 1)

(def magic "ActiveMQ")

(defn encode-command [command]
  (-> (ByteBuffer/allocate 1)
      (.put (byte command))
      (.array)))

(defn encode-4bytes-int [int]
  (-> (ByteBuffer/allocate 4)
      (.putInt int)
      (.array)))

(defn encode-magic []
  (let [buffer (ByteBuffer/allocate (count magic))]
    (dorun (for [b magic]
             (.put buffer (byte b))))
    (.array buffer)))

(defn hex-representation [bytes]
  (->> (map #(format "%02x" %) bytes) (clojure.string/join " ")))

(defn print-bytes [bytes]
  (print (hex-representation bytes)))

(defn print-instructions [instructions]
  (->> instructions
       (map hex-representation)
       (clojure.string/join " ")
       (print))
  (println))

(print-instructions
  [(encode-4bytes-int 316)
   (encode-command 1)
   (encode-magic)])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
