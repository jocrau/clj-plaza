;; @author Antonio Garrote
;; @email antoniogarrote@gmail.com
;; @date 31.05.2010

(ns plaza.rdf.implementations.stores.mulgara
  (:use (plaza.rdf core)
        (plaza.rdf.implementations jena))
  (:import [java.util UUID]
           [java.net URL]))

(defmethod build-model [:mulgara]
  ([& opts]
     (let [options (apply hash-map (rest opts))
           conn-factory (org.mulgara.connection.ConnectionFactory.)
           conn (.newConnection conn-factory (java.net.URI. (:rmi options)))
           jenna-conn (.getJenaConnection conn)
           graph (if (:graph-url options) (:graph-url options) "http://plaza.org/models/default")]
       (plaza.rdf.implementations.jena.JenaModel. (.createModel jenna-conn graph)))))
