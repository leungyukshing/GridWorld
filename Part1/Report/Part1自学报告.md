# 中山大学软件工程中级实训Part1自学报告

#### 数据科学与计算机学院
#### 梁育诚
#### 16340133

## Vi/Vim
&emsp;&emsp;大一的时候在上程序设计课程的时候就已经接触过Vim这款编辑器，它是一款Linux系统下的常见代码编辑器，可以支持直接在命令行中编辑文本，但是它的界面相比起其他编辑器如sublime text或者Atom等，没有相关的语法高亮，同时，Vim也不支持鼠标的点击、括号的补全和换行缩进，因此从方便性来说个人不是很喜欢使用Vim。
&emsp;&emsp;直接在命令行中输入`vim`可以打开Vim编辑器编辑代码，或者`vim filename`来直接打开一个已经存在的代码文件。编辑完成后，先按`Esc`，再输入`:wq`保存退出。

&emsp;&emsp;命令行打开和退出Vim的操作：


## Java
&emsp;&emsp;本次实验的云桌面已经配好了java的开发环境，因此不需要自己配置。在大一的寒假，我自学了一部分java的知识，在windows平台上也配置过java的开发环境，主要分为两步：

  + 上官网下载JDK
  + 配置系统的环境变量
windows下的配置过程有兴趣了解的话可以访问我的博客https://leungyukshing.github.io/archives/Set-up-Java-Environment.html。

&emsp;&emsp;为了更好地学习java，我一直使用的是命令行编译的模式来运行代码，主要分为两步（以HelloWorld.java为例）：
```
javac HelloWorld.java
java HelloWorld
```
需要注意的是文件名必须和主类的名字相一致，否则JVM会因为找不到程序入口而报错。
&emsp;&emsp;在java语法方面，因为有C的基础，所以大多数的语法是没有问题的。所以入门的难度不大。然而两者之间还是有一定的区别。Java的package、interface、GC是相比起C++而言更具优势的地方，在设计的结构和形式上会有略微的不同，但总的来说相差不大。

&emsp;&emsp;这是我的calculator小程序的运行截图：
![calculator运行截图](https://github.com/leungyukshing/GridWorld/blob/master/Part1/Images/calculator.png)

## Ant
&emsp;&emsp;同样地，Ant也是云平台已经安装并配置好的，不需要自己配置。如果需要自己配置，也可以直接通过包管理的方法，在命令行输入
```
sudo apt-get install ant
```
来进行安装。

&emsp;&emsp; Ant主要有三大功能：
① 对制定文件或目录的删除、移动、创建
② 部署编译java程序

&emsp;&emsp;它的语法主要有几点：
① project: 每个project是一个大的任务。
② target：每个target更像是一个步骤，可以自己为这个步骤命名。自己指定default值和basedir。当然还有最重要的depends，意思就是执行当前的这个target要后于depends中的target的执行。
③ 命令元素：target中可以放置许多这些类似于命令的元素，每一对标签都代表着一个命令，当然他们的参数会不一样，用到的时候上网查文档就可以了。这里我主要用到的是clean,mkdir,javac,java这几个。

&emsp;&emsp;执行的方法很简单，在一个项目文件下创建自己的ant文件，一般命名为`build.xml`，然后在命令行中输入`ant`命令就可以了。在自学的过程中，我尝试用ant命令编译和运行`HelloWorld.java`，但是一直都只是编译成功，在控制台没有输出"HelloWorld"，后来自己检查了一下`build.xml`文件后发现，是`project`中的**default**写错了。这里的**default**可以简单地理解为我最终要做的事情，通过找到名为**default**属性字段的**target**，根据其**depends**依赖关系，一层一层地往上找。因此，我就需要把最后一步运行的命令设置是**default**，修改完成后运行成功。

为`HelloWorld.java`编写了测试类，运行`ant`命令后结果：
![](https://github.com/leungyukshing/GridWorld/blob/master/Part1/Images/ant.png)

## Junit
&emsp;&emsp;相比起Ant的学习，Junit的自学比较简单。首先我说一下我对Junit的理解。它用于项目的单元测试。之前我们写的测试类有很大的麻烦，一是测试类必须继承TestCase，二是测试的方法必须以test开头，对于一个比较大的类而言，编写一个测试类是很麻烦的，因此我们使用Junit。Junit允许程序员使用`@Test`来描述测试的数据。我们可以使用`assertEquals()`对输出结果进行判定。如果符合预期输出，则输出true，否则报错。

&emsp;&emsp;云平台也是已经配置好了Junit的环境，但是在实际测试的时候，需要把压缩包拉进测试项目的根目录下，并且在编译和运行测试类的时候，都需要把路径标出来。
```
javac -classpath .:junit-4.10.jar HelloWorldTest.java
java -classpath .:junit-4.10.jar -ea org.junit.runner.JUnitCore HelloWorldTest
```


&emsp;&emsp;同时我也可以将junit写在`build.xml`中，使用`ant`命令一次过实现对一个程序的编译和测试。

## Sonar
&emsp;&emsp;作为一个用于代码质量管理的开源平台，Sonar可用作对代码的管理和优化。自己刚学java的时候虽然很多时候写的东西在编译上没有报错，但实际上可能结构不是那么好，或者说有很多冗余的东西，使用Sonar就可以检测出来。同时也可以用Sonar规范代码风格、优化复杂度、添加注释等。
&emsp;&emsp;我对于自己编写的`calculator.java`代码进行了检测，第一次检测的分数不高，major错误也有几个，经过仔细修改后，包括优化了一些冗余的判断语句等，成功地将major全部修复。最终的测试结果如下：
![sonar运行](https://github.com/leungyukshing/GridWorld/blob/master/Part1/Images/sonar1.png)
![sonar测试结果](https://github.com/leungyukshing/GridWorld/blob/master/Part1/Images/sonar.png)
