#!/bin/bash

shadow(){

  npm i --no-package-lock
  clj -A:dev -M -m shadow.cljs.devtools.cli $1 $2

}

watch(){
  mkdir -p out
  cp src/Bo_Katan/index.html out
  cp src/Bo_Katan/style.css out
  shadow watch :main
}

compile(){
  mkdir -p out
  cp src/Bo_Katan/index.html out
  cp src/Bo_Katan/style.css out
  shadow compile :main
}

repl(){
  shadow cljs-repl $1
}

cljs_compile(){
    clj -m cljs.main -co cljs-build.edn -c
    #  clj -A:dev -m cljs.main -co cljs-build.edn -v -c # -r
}

release(){
  npm i --no-package-lock
  rm -rf out/*.vsix
  mkdir -p out
  npx vsce package --out "out/Bo-Katan-$(git rev-parse --short HEAD).vsix"
}

"$@"