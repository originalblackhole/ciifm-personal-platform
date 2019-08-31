#!/bin/sh
rm -f tpid
APP_NAME=ciifm-personal-admin
nohup java -jar /data/ciifm/$APP_NAME/apps/$APP_NAME.jar --spring.config.location=/data/ciifm/$APP_NAME/config/application.yml > /dev/null 2>&1 &
var=.
tip=start
for i in {1..60}
do
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo $! > tpid
	echo 'start success!'
	break
fi
if [[($i = 60)]];then
	echo -en '\r start fail!'
	break
fi
tip=$tip$var
echo -en "\r $tip"
sleep 1
done
