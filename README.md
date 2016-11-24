[![Build Status](https://travis-ci.org/cyber-dojo/start-points-custom.svg?branch=master)](https://travis-ci.org/cyber-dojo/start-points-custom)

<img src="https://raw.githubusercontent.com/cyber-dojo/nginx/master/images/home_page_logo.png" alt="cyber-dojo yin/yang logo" width="50px" height="50px"/>

The git repo for [cyber-dojo's](https://github.com/cyber-dojo/web)
[setup a new practice session from a <em>custom</em> start point] choices.

![exercises](https://1.bp.blogspot.com/-DLGWLwukncA/V7QGEG-xaiI/AAAAAAAAEq8/z-PeBE-fKhk7gJkw1i1URfBQYSS44VWsACLcB/s320/setup_custom.png)

You can specify and use an alternative custom start-point.

Assuming the files in the specified repo have the
[correct format](http://blog.cyber-dojo.org/2016/08/creating-your-own-start-points.html):

```
$ ./cyber-dojo start-point create --help
$ ./cyber-dojo start-point create seb --git=https://github.com/seb/start-points-custom.git
$ ./cyber-dojo up --custom=seb
```
