#!/bin/sh

#for i in {1..100000}
for i in {1..1000}
do
	#curl localhost:8080/hello &
        curl http://localhost:8080/api/articles &
        sleep 0.1
done
