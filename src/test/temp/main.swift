print("Hello, swift!")

// 使用func来声明一个函数，使用名字和参数来调用函数。使用->来指定函数返回值的类型。
func greet(person: String, day: String) -> String {
    return "Hello \(person), today is \(day)."
}
let result1 = greet(person:"Bob", day: "Tuesday") // person 和 day 为默认参数标签
print(result1)

// 默认情况下，函数使用它们的参数名称作为它们参数的标签，在参数名称前可以自定义参数标签，或者使用 _ 表示不使用参数标签。
func greet(_ person: String, on day: String) -> String { // TODO function name could be the same one, sa long as they have different params
    return "Hello \(person), today is \(day)."
}
let result2 = greet("John", on: "Wednesday") // on 为自定义参数标签
print(result2)

// 使用元组来让一个函数返回多个值。该元组的元素可以用名称或数字来表示。
func calculateStatistics(scores: [Int]) -> (min: Int, max: Int, sum: Int) {
    var min = scores[0]
    var max = scores[0]
    var sum = 0

    for score in scores {
        if score > max {
            max = score
        } else if score < min {
            min = score
        }
        sum += score
    }

    return (min, max, sum)
}
let statistics = calculateStatistics(scores:[5, 3, 100, 3, 9])
print(statistics.sum)
print(statistics.2)

// 可变个数的参数
func sumOf(numbers: Int...) -> Int {
    var sum = 0
    for number in numbers { // 参数 numbers 在函数内表现为数组的形式
        sum += number
    }
    return sum
}
let sum1 = sumOf()
print(sum1)
let sum2 = sumOf(numbers: 42, 597, 12)
print(sum2)

// 函数可以嵌套
func returnFifteen() -> Int {
    var y = 10
    func add() {
        y += 5
    }
    add()
    return y
}
let add = returnFifteen()
print(add)

// 函数是第一等类型
// -- 这意味着函数可以作为另一个函数的返回值。
func makeIncrementer() -> ((Int) -> Int) {
    func addOne(number: Int) -> Int {
        return 1 + number
    }
    return addOne
}
var increment = makeIncrementer()
let increm = increment(7)
print(increm)
// -- 也可以当做参数传入另一个函数。
func hasAnyMatches(list: [Int], condition: (Int) -> Bool) -> Bool {
    for item in list {
        if condition(item) {
            return true
        }
    }
    return false
}
func lessThanTen(number: Int) -> Bool {
    return number < 10
}
var numbers = [20, 19, 7, 12]
let match = hasAnyMatches(list: numbers, condition: lessThanTen)
print(match)






