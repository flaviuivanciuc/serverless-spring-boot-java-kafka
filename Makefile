.phony: build-app deploy

build-app:
	./gradlew clean build

clean-app:
	./gradlew clean &&\
	rm -R -f node_modules

deploy:
	./node_modules/.bin/sls deploy --verbose --stage $(ENVIRONMENT)

remove:
	./node_modules/.bin/sls remove --stage $(ENVIRONMENT)

install:
	rm -R -f node_modules &&\
	yarn install
