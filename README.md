# Tell

[![CircleCI](https://circleci.com/gh/paschelino/tell/tree/master.svg?style=svg)](https://circleci.com/gh/paschelino/tell/tree/master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/cc0e2121bf984af69cda0cb3c0f785ee)](https://www.codacy.com/app/paschelino/tell?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=paschelino/tell&amp;utm_campaign=Badge_Grade)

Besides that [Wilhelm Tell](https://en.wikipedia.org/wiki/William_Tell) is the big liberty hero
in the myths of the [canton of Uri](https://en.wikipedia.org/wiki/Canton_of_Uri) in Switzerland,
Tell is a URI builder library written in Kotlin. It's implementation builds upon the relevant
RFCs for URIs, which are:
* [RFC 3986 - Uniform Resource Identifier (URI): Generic Syntax](https://tools.ietf.org/html/rfc3986) _in progress_
* [RFC 8141 - Uniform Resource Names (URNs)](https://tools.ietf.org/html/rfc8141) _TODO_

## Why would one need this lib? Are'nt Strings enough or even of better performance?

The driving ideas to this library are
* to have a smart DSL to assemble URIs with the safety of a compiler and thus to get rid of 
  primitive obsession while building and passing around URIs via Strings, which is error prone and 
  hard to debug 
* to allow fine grained validation of each part of an URI
* to allow you to extend the DSL with your own types, which gives you a lot of expressiveness and
  power for the routing inside your application
* to have classifiers for important parts of the URI, which can be used by aspects, if needed

It's a fact, that an URI, although seemingly short to our perception, is one of the quite complex
syntactic constructs in the context of the web. Web applications working with String primitives 
tend to produce maintenance nightmares. It can be bloody mess to debug, how a malformed URL was
built, especially if it is being passed around and modified between different software modules or
even multiple services in a micro service landscape. Furthermore it happend several times in 
projects I've been part of, that a given subset of URIs were subject to changed requirements - 
either regarding form or parameters or whatever. That's a common case, where a development team 
faces a shotgun surgery throughout the code base to modify all these URI occurrences of the given 
subset - to add a query parameter or a path prefix for example. Common hacks to fix this mess 
produce servlet filters, rewriting all matching links before the server response is committed to the
client. This is a real performance killer, since the whole HTML document needs to be processed with
according regular expression matches and even be modified. This type of modification is hard to
test and hard to control in real life production scenarios. If instead this subset of URIs is 
classified and thus can be accessed by an aspect, such modifications become very simple.

Ah! And by the way: Please forget about that performance nonsense...
(Not convinced? [Have a look at this!]()) 

## A real world example...
TBD

## Your own DSL? Dead simple!
TBD

## Add tracking callbacks to a subset of your links (...the aspect thingy...)
TBD

