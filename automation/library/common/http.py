
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

    @staticmethoong'}
        HttpClient(url).http_put(dicts)
        time.sleep(5);
        counter = counter + 1
