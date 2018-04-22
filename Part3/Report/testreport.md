# Test Report(Part 3)
## Academy 数据科学与计算机学院
## Name 梁育诚
## No 16340133

## 程序名称: Jumper

## 测试原理
&emsp;&emsp;本次程序的测试使用Junit。测试的原理是，通过运行JumperTest，对实际的Jumper的位置和预期的位置的行和列进行对比，如果结果一致，则说明测试成功，否则测试失败。

## 测试步骤
1. 编写了JumperTest.java，其中建立了三种情况的测试：分别是在没有阻拦的情况下正常跳跃、在有石头的情况下的跳跃、两个Jumper相对的条约。
2. 然后在纸上自己模拟出三种情况下的结果，将预期结果和程序运行的结果作对比。
3. 将Junit编写进build.xml中，运行ant命令进行测试。
4.
测试情况1：

![情况1](https://github.com/leungyukshing/GridWorld/blob/master/Part3/Images/test1.png?raw=true)

测试情况2：

![情况2](https://github.com/leungyukshing/GridWorld/blob/master/Part3/Images/test2.png?raw=true)

测试情况3：

![情况3](https://github.com/leungyukshing/GridWorld/blob/master/Part3/Images/test3.png?raw=true)

测试结果：

![Junit运行结果](https://github.com/leungyukshing/GridWorld/blob/master/Part3/Images/result.png?raw=true)

## 总结
&emsp;&emsp;自己写了三种情况下的测例，虽然不能说保证代码的完全正确性，但至少涵盖了我认为比较容易出问题的几种情况。对于三种情况下，测试的结果均为正确。
