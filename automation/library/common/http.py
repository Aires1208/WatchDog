# -*- coding:utf-8 -*-
import json
import urllib2

import time


class HttpClient(object):
    def __init__(self):
        pass

    @staticmethod
    def get(self, url):
        response = urllib2.urlopen(url)
        return response.read()

    @staticmethod    
    def post(self, url, dicts):
        request = urllib2.Request(url, json.dumps(dicts))
        request.add_header('Content-Type', 'application/json')
        request.get_method = lambda: 'POST'
        response = urllib2.urlopen(request)
        return response.read()

    @staticmethod
    def put(self, url, dicts):
        request = urllib2.Request(url, json.dumps(dicts))
        request.add_header('Content-Type', 'application/json')
        request.get_method = lambda: 'PUT'
        response = urllib2.urlopen(request)
        return response.read()

    @staticmethod
    def delete(self, url, dicts):
        request = urllib2.Request(url, json.dumps(dicts))
        request.add_header('Content-Type', 'application/json')
        request.get_method = lambda: 'DELETE'
        response = urllib2.urlopen(request)
        return response.read()


if __name__ == '__main__':
    counter = 0
    while True:
        print '--------------------------------------------------------------------'
        print counter
        print RESTClient('http://127.0.0.1:5000/api/v1.0/tasks').http_get()
        print '--------------------------------------------------------------------'
        url = 'http://127.0.0.1:5000/api/v1.0/tasks/' + str(counter)
        dicts = {'task_name': 'qianguodong'}
        HttpClient(url).http_put(dicts)
        time.sleep(5);
        counter = counter + 1