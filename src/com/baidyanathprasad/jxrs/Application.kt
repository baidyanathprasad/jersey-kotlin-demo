package com.baidyanathprasad.jxrs

import org.glassfish.jersey.server.ResourceConfig

class Application : ResourceConfig() {
    
    init {
        // Configure resource files for end points
        packages(this.javaClass.`package`?.name + ".resources")
    }
}
