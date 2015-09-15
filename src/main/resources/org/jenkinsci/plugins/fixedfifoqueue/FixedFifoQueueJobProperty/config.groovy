package org.jenkinsci.plugins.fixedfifoqueue.FixedFifoQueueJobProperty

import org.jenkinsci.plugins.fixedfifoqueue.FixedFifoQueueJobProperty

def f=namespace(lib.FormTagLib)

f.optionalBlock(title:_("Limit number of concurrent items in queue"), name:"fixedfifo",
        checked:instance!=null) {
    f.entry(field: "limit") {
        f.number()
    }
}