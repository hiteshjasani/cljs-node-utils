
compile:
	lein cljsbuild once

test: compile
	node target/test-node.js
	node target/test-simple.js
	node target/test-adv.js

uberjar:
	lein uberjar

clean:
	lein clean

