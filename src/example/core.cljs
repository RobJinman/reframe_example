(ns example.core
  (:require 
   [goog.dom :as gdom]
   [reagent.core :as reagent]
   [re-frame.core :refer [reg-event-db
                          reg-event-fx
                          reg-fx
                          reg-cofx
                          inject-cofx
                          reg-sub
                          dispatch
                          dispatch-sync
                          subscribe]]))


;; -- Definitions ---------------------------------------------------------



;; -- Pure Helper Functions -----------------------------------------------



;; -- Impure Helper Functions ---------------------------------------------

(defn posix-time!
  []
  (/ (.getTime (js/Date.)) 1000))


;; -- Coeffect Handlers ---------------------------------------------------

(reg-cofx
 :posix-time
 (fn [cofx]
   (assoc cofx :posix-time (posix-time!))))


;; -- Effect Handlers -----------------------------------------------------

(reg-fx
 :do-side-effect
 (fn [{:keys [arg1 arg2]}]
   (do))) ;; TODO


;; -- Event Handlers ------------------------------------------------------

(reg-event-fx
 :initialise
 (fn [cofx _]
   {:db {:msg "Hello, World!"}}))


;; -- Subscription Handlers -----------------------------------------------

(reg-sub
 :msg
 (fn [state _]
   (get-in state [:msg])))


;; -- View Components -----------------------------------------------------

(defn sub-view
  []
  (let [msg (subscribe [:msg])]
    [:div (str @msg)]))

(defn main-view
  []
  (fn []
    [:div
     [:h3 "ClojureScript Example Project"]
     [:p "This is just a barebones clojurescript app with re-frame."]
     [sub-view]]))


;; -- Entry Point ---------------------------------------------------------

(defn ^:export run
  []
  (dispatch-sync [:initialise])
  (reagent/render [main-view]
                  (gdom/getElement "example-root")))
