(defproject example "0.1.0-SNAPSHOT"
  :description "Example ClojureScript project"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.227"]
                 [org.clojure/test.check "0.9.0"]
                 [reagent "0.6.0-rc"]
                 [re-frame "0.8.0"]]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-figwheel "0.5.4-7"]
            [lein-resource "15.10.2"]
            [lein-less "1.7.5"]]

  :hooks [leiningen.cljsbuild]

  :profiles {:dev {:cljsbuild
                   {:builds {:client {:source-paths ["devsrc"]
                                      :compiler {:main "example.dev"
                                                 :asset-path "js"
                                                 :optimizations :none
                                                 :source-map true
                                                 :source-map-timestamp true}}}}}
             :prod {:cljsbuild
                    {:builds {:client {:compiler {:optimizations :advanced
                                                  :elide-asserts true
                                                  :pretty-print false}}}}}}

  :less {:source-paths ["less"]
         :target-path  "resources/public/css"}

  :cljsbuild {:builds {:client {:source-paths ["src"]
                                :compiler {:output-dir "resources/public/js"
                                           :output-to  "resources/public/js/example.js"}}
                       :release {:source-paths ["src"]
                                 :compiler     {:optimizations  :advanced
                                                :pretty-print   false
                                                :output-dir     "target/js/out"
                                                :output-to      "target/js/example.js"
                                                :source-map     "target/js/example.js.map"
                                                :parallel-build true}}}}

  :resource {:resource-paths ["resources/public"]
             :target-path    "target"
             :update         false
             :includes       [#".*"]
             :excludes       [#".*~"
                              #".*/\.gitignore"
                              #"resources/public/js/.*"]
             :silent         false
             :verbose        false
             :skip-stencil   [#"resources/public/css/.*"
                              #"resources/public/img/.*"]
             :extra-values   nil}

:aliases {"build" ["do" "clean"
                   ["cljsbuild" "once" "release"]
                   ["less" "once"]
                   ["resource"]]})

