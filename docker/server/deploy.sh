#!/bin/sh

is_exists(){
  if [ -f "watchdog-server.war" ];then
    echo "delete watchdog-server.war"
    rm -rf watchdog-server.war
  else
    echo "do not have watchdog-server.war"
   fi
}

copy_version(){
  if [ -f "../../server/target/watchdog-server-0.1.0-SNAPSHOT.war" ];then
    echo "copy version ..."
    cp ../../server/target/watchdog-server-0.1.0-SNAPSHOT.war ./watchdog-server.war
  else
    echo "do not have watchdog-server-0.1.0-SNAPSHOT.war, errors..."
    exit 1
  fi
}

docker_build(){
  docker build -t watchdog-server:0.1.0 .
}

container_delete(){
  for container_id in `docker ps -a | grep watchdog-server | grep -v grep | awk '{print $1}'`
  do
    echo "docker rm watchdog server process,$container_id"
    docker rm -f $container_id
    sleep 0.5s
  done
}

image_delete(){
  for images_id in `docker images | grep watchdog-server | grep -v grep | awk '{print $3}'`
  do
    echo "docker rmi watchdog server images,$images_id"
    docker rmi $images_id
    sleep 0.5s
  done
}

run_container(){
  docker run -d -p 8080:8080 --name watchdog-server watchdog-server:0.1.0
}

######################################
############# main ###################
######################################
container_delete
image_delete
is_exists
copy_version
docker_build
run_container