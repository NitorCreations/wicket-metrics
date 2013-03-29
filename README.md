wicket-metrics
==============

Painless way of adding JMX [metrics](http://metrics.codahale.com/) to your [Wicket](http://wicket.apache.org/) project.

## Usage

Start by addding the following Maven dependency:

    <dependency>
        <groupId>com.nitorcreations</groupId>
        <artifactId>wicket-metrics</artifactId>
        <version>1.0</version>
    </dependency>
    
After this, three types of metrics can be added:

1. Page size
  * This will show the size (min, max, avg, etc) of each rendered page in your web app
2. Render time
  * This will show the time (min, max, avg, etc) it too your server the render the given page
3. Session size
  * This is single metric that tracks the size of your Wicket session
  
