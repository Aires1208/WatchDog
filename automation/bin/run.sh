#!/bin/bash

current_dir=`pwd`

base_dir=`dirname $current_dir`
cd $current_dir
mkdir -p ${current_dir}/log
export PYTHONPATH=${PYTHONPATH}:${base_dir}:${base_dir}/service

para=" --outputdir ${current_dir}/log --name WATCHDOG --loglevel INFO"
pybot ${para} ${base_dir}/testcase