.phony: build-app deploy clean-app remove invoke-local install

build-app:
	./gradlew clean build

clean-app:
	./gradlew clean &&\
	rm -R -f node_modules

deploy:
	./node_modules/.bin/sls deploy --verbose --stage $(ENVIRONMENT)

remove:
	./node_modules/.bin/sls remove --stage $(ENVIRONMENT)

invoke-local:
	./node_modules/.bin/sls invoke local --function kafka --data '{"eventSource":"SelfManagedKafka","bootstrapServers":"b-2.demo-cluster-1.a1bcde.c1.kafka.us-east-1.amazonaws.com:9092,b-1.demo-cluster-1.a1bcde.c1.kafka.us-east-1.amazonaws.com:9092","records":{"mytopic-0":[{"topic":"mytopic","partition":0,"offset":15,"timestamp":1545084650987,"timestampType":"CREATE_TIME","key":"abcDEFghiJKLmnoPQRstuVWXyz1234==","value":"SGVsbG8sIHRoaXMgaXMgYSB0ZXN0Lg==","headers":[{"headerKey":[104,101,97,100,101,114,86,97,108,117,101]}]}]}}'

install:
	rm -R -f node_modules &&\
	yarn install
