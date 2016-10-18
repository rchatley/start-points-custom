[![Build Status](https://travis-ci.org/cyber-dojo/start-points-custom.svg?branch=master)](https://travis-ci.org/cyber-dojo/start-points-custom)

<img src="https://raw.githubusercontent.com/cyber-dojo/nginx/master/images/home_page_logo.png" alt="cyber-dojo yin/yang logo" width="50px" height="50px"/>

The git repo for [cyber-dojo's](https://github.com/cyber-dojo/web)
[setup a new practice session from a <em>custom</em> start point] choices.

You can specify and use an alternative custom start-point.
Assuming https://github.com/seb/start-points-custom.git has the correct format:

```
$ sudo ./cyber-dojo start-point create --help
$ sudo ./cyber-dojo start-point create seb --git=https://github.com/seb/start-points-custom.git
$ sudo ./cyber-dojo up --custom=seb
```
