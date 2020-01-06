f = {e-> e }
def f1(e){e}
p = {args->print(args)}

def func = { text,closure ->
    println text
    closure()
}
// 会当成函数调用---定义函数需加收def或用f={args->ret}
func(5){println 6 }//---- 简介
// =
func(5,{println 6 })//清晰

println f(12)
println f1(13)


d = {d,p-> p(d)}
d (1)  {e->println e+2}


def name = 'Tom'
def greeting = "Hello ${name}"
println(greeting)


ff = {
    println(it)//默认参数名it
}
ff(55)
