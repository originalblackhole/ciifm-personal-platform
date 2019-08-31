#!/bin/sh
APP_NAME=ciifm-personal-admin
path=/data/ciifm/$APP_NAME
pidlist=`ps -ef|grep $APP_NAME |grep -v "grep"|awk '{print $2}'`
if [ "$pidlist" = "" ]
then
echo "no $APP_NAME pid alive!"
else
echo "$APP_NAME pid list :  $pidlist"
echo "killing pidlist :  $pidlist"
kill -9 $pidlist
echo "$APP_NAME stopped successfully!"
sleep 1
fi
echo "now starting $APP_NAME......"
cd $path
./start.sh
