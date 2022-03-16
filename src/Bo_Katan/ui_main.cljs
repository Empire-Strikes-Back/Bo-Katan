(ns Bo-Katan.ui-main
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
   [clojure.pprint :refer [pprint]]

   [reagent.core :as r]
   [reagent.dom]
   ["react" :as React :refer [useEffect]]
   ["antd/lib/layout" :default AntLayout]
   ["antd/lib/menu" :default AntMenu]
   ["antd/lib/icon" :default AntIcon]
   ["antd/lib/button" :default AntButton]
   ["antd/lib/list" :default AntList]
   ["antd/lib/row" :default AntRow]
   ["antd/lib/col" :default AntCol]
   ["antd/lib/form" :default AntForm]
   ["antd/lib/input" :default AntInput]
   ["antd/lib/tabs" :default AntTabs]
   ["antd/lib/table" :default AntTable]
   ["antd/lib/tag" :default AntTag]

   ["antd/lib/checkbox" :default AntCheckbox]


   ["antd/lib/divider" :default AntDivider]
   ["@ant-design/icons/SmileOutlined" :default AntIconSmileOutlined]
   ["@ant-design/icons/LoadingOutlined" :default AntIconLoadingOutlined]
   ["@ant-design/icons/SyncOutlined" :default AntIconSyncOutlined]
   ["@ant-design/icons/ReloadOutlined" :default AntIconReloadOutlined]))


(declare current-page
         routes
         send-data)

(defonce matchA (r/atom nil))
(defonce stateA (r/atom {}))

(defn current-page [matchA]
  (r/with-let
    []
    (let []
      [:div "i'm changing the terms of the deal - this is the way"])))

(defn create
  [{:as opts
    :keys [:recv|
           :send|]}]
  (let [inputs| (chan 10)]
    (reagent.dom/render [current-page matchA] (.getElementById js/document "ui"))))

(declare vscode)

(when (exists? js/acquireVsCodeApi)
  (defonce vscode (js/acquireVsCodeApi)))

(defn ^:export -main
  []
  (println :main)
  (let [recv| (chan 10)
        send| (chan 10)]
    (create
     {:recv| recv|
      :send| send|})
    (.addEventListener js/window "message"
                       (fn [ev]
                         (put! recv| (read-string ev.data))))
    (go
      (loop []
        (when-let [msg (<! send|)]
          (.postMessage vscode msg)
          (recur))))))

(defn ^:export reload
  []
  (println ::reload))

(do (-main))