# Seraph query parser

An ANTLR4 parser for Seraph continuous graph queries.

This project is still under development.

## Syntax
The syntax of Seraph extends the syntax of Cypher 9 as follows:
![](syntax.png) 

## Usage examples

**Query 1**: \
&ensp; <span style="color:  #3498db ">
REGISTER QUERY
</span> `<query_name>` <span style="color:  #3498db ">
STARTING AT
</span> `2019-09-07T15:50:30.022+01:00` {
<span style="color:  #3498db ">
MATCH
</span> `(a) --> (b) --> (c)` <span style="color:  #3498db ">
WITHIN
</span> `PT1H`
&ensp; &ensp; <span style="color:  #3498db ">
WHERE
</span> `a = 1 OR b = 2` <span style="color:  #3498db ">
<span style="color:  #3498db ">
RETURN
</span> `c`
ON EXIT EVERY
</span> `PT10M` }

**CQ-Object 1**: \
&ensp; *id* = `query_name` \
&ensp; *start_time* =  `2019-09-07T15:50:30.022+01:00` \
&ensp; Match-Clause-Object: \
&ensp; &emsp; &emsp; *pattern* = `(a) --> (b) --> (c)` \
&ensp; &emsp; &emsp; *duration* = `PT1H` \
&ensp; &emsp; &emsp; *where_exp* = `a = 1 OR b = 2` \
&ensp; *stream_operator* = <span style="color:  #3498db ">
ON EXIT 
</span> \
&ensp; *duration* = `PT10M`

**QUERY 2**:
&ensp; <span style="color:  #3498db ">
REGISTER QUERY
</span> `<query_name>` <span style="color:  #3498db ">
STARTING AT
</span>
`2019-09-07T15:50:30.022+01:00` {
<span style="color:  #3498db ">
MATCH
</span> `(a) --> (b) <-- (c)`  <span style="color:  #3498db ">
WITHIN
</span> `PT1H` <span style="color:  #3498db ">
RETURN
</span> * ON EXIT EVERY P5M30DT10M}




<span style="color:  #3498db ">
REGISTER QUERY
</span>

<span style="color:  #3498db ">
STARTING AT
</span>

<span style="color:  #3498db ">
MATCH
</span>

<span style="color:  #3498db ">
WITHIN
</span>

<span style="color:  #3498db ">
WHERE
</span>

<span style="color:  #3498db ">
ON EXIT EVERY
</span>

<span style="color:  #3498db ">
RETURN
</span>
## License

Licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
