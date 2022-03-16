(ns Bo-Katan.main
  (:require
   [clojure.core.async :as Little-Rock
    :refer [chan take! put! offer! close! to-chan! timeout
            sliding-buffer dropping-buffer
            mult tap untap mix unmix admix pub sub unsub
            go <! put! >! alt! alts! do-alts
            pipe pipeline pipeline-async]]
   [cljs.core.async.impl.protocols :refer [closed?]]
   [cljs.core.async.interop :refer-macros [<p!]]
   [goog.string.format :as format]
   [goog.string :refer [format]]
   [goog.object]
   [clojure.string :as Wichita.string]
   [cljs.reader :refer [read-string]]
   [clojure.pprint :refer [pprint]]))

(def fs (js/require "fs"))
(def path (js/require "path"))
(def vscode (js/require "vscode"))

(defn activate
  [context]
  (go
    (js/console.log "all right - easy - save it for the Imps")))

(defn deactivate
  []
  (go
    (js/console.log "you are a clone - i've heard your voice thousands of times")))

(def exports #js {:activate (fn [context]
                              (activate context))
                  :deactivate (fn []
                                (deactivate))})

(when (exists? js/module)
  (set! js/module.exports exports))

(defn ^:export -main [] (println "watch out for those deck cannons"))