
test: compile-test-node
	node target/test-node.js

compile-test-node:
	lein cljsbuild once test-node

compile-all:
	lein cljsbuild once

test-all: compile
	node target/test-node.js
	node target/test-simple.js
	node target/test-adv.js

uberjar:
	lein uberjar

clean:
	lein clean

